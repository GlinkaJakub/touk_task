package com.glinka.touk_task.service;

import com.glinka.touk_task.dto.SeatDto;
import com.glinka.touk_task.model.Room;
import com.glinka.touk_task.model.Seat;

import java.util.List;

public interface ISeatService {

    void save(Seat seat);
    Seat findById(int id);
    Seat reservationSeat(int id);

    SeatDto mapToDto(Seat seat);
    List<SeatDto> mapListToDto(List<Seat> seats);
    List<SeatDto> findAllByRoomId(Room room);
}
