package com.glinka.touk_task.controller;

import com.glinka.touk_task.dto.FilmInformationDto;
import com.glinka.touk_task.dto.TicketRequestDto;
import com.glinka.touk_task.dto.TicketResponseDto;
import com.glinka.touk_task.model.Film;
import com.glinka.touk_task.model.Multiplex;
import com.glinka.touk_task.model.Room;
import com.glinka.touk_task.model.Seat;
import com.glinka.touk_task.model.TicketType;
import com.glinka.touk_task.service.IFilmService;
import com.glinka.touk_task.service.IMultiplexService;
import com.glinka.touk_task.service.IRoomService;
import com.glinka.touk_task.service.ISeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FilmControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    IFilmService filmService;
    IRoomService roomService;
    ISeatService seatService;
    IMultiplexService multiplexService;

    @Autowired
    public FilmControllerTest(IFilmService filmService, IRoomService roomService, ISeatService seatService, IMultiplexService multiplexService) {
        this.filmService = filmService;
        this.roomService = roomService;
        this.seatService = seatService;
        this.multiplexService = multiplexService;
    }

    @BeforeEach
    void setUp() {
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

    @Test
    void findAllByDateAndTime() {
        //given
        String date = "2020-01-02";
        String time = "12:13:00";
        HttpEntity<List<Film>> entity = new HttpEntity<>(null, headers);
        //when
        ResponseEntity<Film[]> response = restTemplate.exchange(
                createURLWithPort("/films?date={date}&time={time}"),
                HttpMethod.GET,
                entity,
                Film[].class,
                date, time
        );
        //then
        assertEquals("F&F 4", response.getBody()[0].getTitle());
    }

    @Test
    void findAllByDateBetweenAndTimeBetween() {
        //given
        String startDate = "2020-01-01";
        String endDate = "2020-01-02";
        String startTime = "12:00:00";
        String endTime = "13:00:00";
        HttpEntity<List<Film>> entity = new HttpEntity<>(null, headers);
        //when
        List<Film> result = filmService.findAllByDateAndTime(LocalDate.parse("2020-01-02"), LocalTime.parse("12:13:00"));
        ResponseEntity<Film[]> response = restTemplate.exchange(
                createURLWithPort("/films/time?startDate={startDate}&endDate={endDate}&startTime={startTime}&endTime={endTime}"),
                HttpMethod.GET,
                entity,
                Film[].class,
                startDate, endDate, startTime, endTime
        );
        //then
        assertEquals("Auta 2", response.getBody()[0].getTitle());
    }

    @Test
    void findById() {
        //given
        int id = 6;
        HttpEntity<FilmInformationDto> entity = new HttpEntity<>(null, headers);
        //when
        ResponseEntity<FilmInformationDto> response = restTemplate.exchange(
                createURLWithPort("/films/{id}"),
                HttpMethod.GET,
                entity,
                FilmInformationDto.class,
                id
        );
        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Auta 2", response.getBody().getTitle());
    }

    @Test
    void saveNewTicket() {
        //given
        TicketRequestDto ticket = new TicketRequestDto(6, 3, "Jan", "Kowalski", TicketType.ADULT);
        HttpEntity<TicketRequestDto> entity = new HttpEntity<>(ticket, headers);
        //when
        ResponseEntity<TicketResponseDto> response = restTemplate.exchange(
                createURLWithPort("/ticket"),
                HttpMethod.POST,
                entity,
                TicketResponseDto.class
        );
        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(25, response.getBody().getPrice());
    }

    String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}