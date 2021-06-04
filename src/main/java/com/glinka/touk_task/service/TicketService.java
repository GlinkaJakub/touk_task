package com.glinka.touk_task.service;

import com.glinka.touk_task.dto.TicketRequestDto;
import com.glinka.touk_task.dto.TicketResponseDto;
import com.glinka.touk_task.model.Film;
import com.glinka.touk_task.model.Seat;
import com.glinka.touk_task.model.Ticket;
import com.glinka.touk_task.model.User;
import com.glinka.touk_task.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService implements ITicketService {

    TicketRepository ticketRepository;
    ISeatService seatService;
    IFilmService filmService;
    IUserService userService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, ISeatService seatService, IFilmService filmService, IUserService userService) {
        this.ticketRepository = ticketRepository;
        this.seatService = seatService;
        this.filmService = filmService;
        this.userService = userService;
    }

    @Override
    public TicketResponseDto save(TicketRequestDto ticketRequestDto) {
        Seat seat = seatService.findById(ticketRequestDto.getSeatId());
        Film film = filmService.findById(ticketRequestDto.getFilmId());
        LocalDateTime ldt = LocalDateTime.of(film.getDate(), film.getTime());
        if (ldt.plusMinutes(15).isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("It's not possible to book a ticket.");
        }
        User user = new User(ticketRequestDto.getName(), ticketRequestDto.getSurname());
        userService.save(user);
        Ticket ticket = new Ticket(ticketRequestDto.getType(), user, seat, film);
        ticketRepository.save(ticket);
        seatService.reservationSeat(seat.getId());
        return new TicketResponseDto(ticketRequestDto.getType().getPrice(), LocalDateTime.now().plusDays(3));
    }
}
