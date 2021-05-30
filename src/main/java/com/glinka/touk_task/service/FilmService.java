package com.glinka.touk_task.service;

import com.glinka.touk_task.dto.FilmDto;
import com.glinka.touk_task.dto.FilmInformationDto;
import com.glinka.touk_task.model.Film;
import com.glinka.touk_task.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class FilmService implements IFilmService{

    FilmRepository filmRepository;
    ISeatService seatService;
    IRoomService roomService;

    @Autowired
    public FilmService(FilmRepository filmRepository, ISeatService seatService, IRoomService roomService) {
        this.filmRepository = filmRepository;
        this.seatService = seatService;
        this.roomService = roomService;
    }

    @Override
    public List<Film> findAllByDateAndTime(LocalDate date, LocalTime time) {
        return filmRepository.findAllByDateAndTime(date, time, Sort.by(ASC, "title", "date", "time"));
    }

    @Override
    public List<Film> findAllByDateBetweenAndTimeBetween(LocalDate dateStart, LocalDate dateEnd, LocalTime timeStart, LocalTime timeEnd) {
        return filmRepository.findAllByDateBetweenAndTimeBetween(dateStart, dateEnd, timeStart, timeEnd, Sort.by(ASC, "title", "date", "time"));
    }

    @Override
    public FilmInformationDto findFilmInformationById(int id) {
        return mapToFilmInformationDto(filmRepository.getById(id));
    }

    @Override
    public Film findById(int id) {
        return filmRepository.getById(id);
    }

    @Override
    public void save(FilmDto film) {
        Film s = mapToDao(film);
        filmRepository.save(s);
    }

    public Film mapToDao(FilmDto filmDto){
        return Film.builder()
                .title(filmDto.getTitle())
                .date(LocalDate.parse(filmDto.getDate()))
                .time(LocalTime.parse(filmDto.getTime()))
                .room(roomService.findById(filmDto.getRoomId()))
                .build();
    }

    public FilmDto mapToDto(Film film){
        return FilmDto.builder()
                .title(film.getTitle())
                .date(film.getDate().toString())
                .time(film.getTime().toString())
                .roomId(film.getRoom().getId())
                .build();
    }

    public FilmInformationDto mapToFilmInformationDto(Film film){
        return FilmInformationDto.builder()
                .id(film.getId())
                .title(film.getTitle())
                .date(film.getDate().toString())
                .time(film.getTime().toString())
                .roomName(film.getRoom().getName())
                .seats(seatService.findAllByRoomId(film.getRoom()))
                .build();
    }
}
