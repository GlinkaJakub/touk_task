package com.glinka.touk_task;

import com.glinka.touk_task.model.Film;
import com.glinka.touk_task.model.Multiplex;
import com.glinka.touk_task.model.Room;
import com.glinka.touk_task.model.Seat;
import com.glinka.touk_task.service.IFilmService;
import com.glinka.touk_task.service.IMultiplexService;
import com.glinka.touk_task.service.IRoomService;
import com.glinka.touk_task.service.ISeatService;
import com.glinka.touk_task.service.ITicketService;
import com.glinka.touk_task.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class InitiateData {

    private final IFilmService filmService;
    private final IMultiplexService multiplexService;
    private final IRoomService roomService;
    private final ISeatService seatService;
    private final ITicketService ticketService;
    private final IUserService userService;

    @Autowired
    public InitiateData(IFilmService filmService, IMultiplexService multiplexService, IRoomService roomService, ISeatService seatService, ITicketService ticketService, IUserService userService) {
        this.filmService = filmService;
        this.multiplexService = multiplexService;
        this.roomService = roomService;
        this.seatService = seatService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        Multiplex multiplex = new Multiplex();
        Room room = new Room("room no. 1", multiplex);
        Seat seat1 = new Seat(1, 2, true, room);
        Seat seat2 = new Seat(1, 3, true, room);
        Seat seat3 = new Seat(1, 4, true, room);
        Film film1 = new Film("Auta 2", LocalDate.parse("2020-01-01"), LocalTime.parse("12:12:00"), room);
        Film film2 = new Film("F&F 4", LocalDate.parse("2020-01-02"), LocalTime.parse("12:13:00"), room);
        Film film3 = new Film("F&F 5", LocalDate.parse("2020-01-03"), LocalTime.parse("12:14:00"), room);
        Film film4 = new Film("F&F 6", LocalDate.parse("2020-01-04"), LocalTime.parse("18:12:00"), room);
        multiplexService.save(multiplex);
        roomService.save(room);
        seatService.save(seat1);
        seatService.save(seat2);
        seatService.save(seat3);
        filmService.save(filmService.mapToDto(film1));
        filmService.save(filmService.mapToDto(film2));
        filmService.save(filmService.mapToDto(film3));
        filmService.save(filmService.mapToDto(film4));
    }
}
