package com.glinka.touk_task.service;

import com.glinka.touk_task.dto.SeatDto;
import com.glinka.touk_task.model.Room;
import com.glinka.touk_task.model.Seat;
import com.glinka.touk_task.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService implements ISeatService {

    SeatRepository seatRepository;
    IRoomService roomService;

    public SeatService(SeatRepository seatRepository, IRoomService roomService) {
        this.seatRepository = seatRepository;
        this.roomService = roomService;
    }

    @Override
    public void save(Seat seat) {
        seatRepository.save(seat);
//        roomService.addSeatsToRoom(seat.getRooms().getId(), seat);
    }

    @Override
    public Seat reservationSeat(int id) {
        Seat seat = findById(id);
        seat.setAvailable(false);
        return seatRepository.save(seat);
    }

    @Override
    public Seat findById(int id) {
        return seatRepository.getById(id);
    }

    public SeatDto mapToDto(Seat seat){
        return SeatDto.builder()
                .id(seat.getId())
                .row(seat.getSeatRow())
                .seatNumber(seat.getSeatNumber())
                .build();
    }

    public List<SeatDto> mapListToDto(List<Seat> seats){
        return seats.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<SeatDto> findAllByRoomId(Room room) {
        return mapListToDto(seatRepository.findAllByRoomsAndAndAvailable(room.getId(), true));
    }
}
