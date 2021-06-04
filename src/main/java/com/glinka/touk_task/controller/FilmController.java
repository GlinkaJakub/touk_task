package com.glinka.touk_task.controller;

import com.glinka.touk_task.dto.FilmDto;
import com.glinka.touk_task.dto.FilmInformationDto;
import com.glinka.touk_task.model.Film;
import com.glinka.touk_task.service.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class FilmController {

    IFilmService filmService;

    @Autowired
    public FilmController(IFilmService filmService) {
        this.filmService = filmService;
    }

    @RequestMapping(value = "/films", method = RequestMethod.GET)
    public ResponseEntity<List<Film>> findFilmsByDateAndTime(String date, String time){
        List<Film> films = filmService.findAllByDateAndTime(LocalDate.parse(date), LocalTime.parse(time));
        return ResponseEntity.ok(films);
    }

    @RequestMapping(value = "/films/time", method = RequestMethod.GET)
    public ResponseEntity<List<Film>> findAllFilmsBetweenSelectDate(String startDate, String endDate, String startTime, String endTime){
        List<Film> films = filmService.findAllByDateBetweenAndTimeBetween(LocalDate.parse(startDate),
                LocalDate.parse(endDate),
                LocalTime.parse(startTime),
                LocalTime.parse(endTime));
        return ResponseEntity.ok(films);
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.GET)
    public ResponseEntity<FilmInformationDto> findById(@PathVariable int id){
        FilmInformationDto film = filmService.findFilmInformationById(id);
        return ResponseEntity.ok(film);
    }

    @RequestMapping(value = "/films", method = RequestMethod.POST)
    public ResponseEntity<String> saveNewFilm(@RequestBody FilmDto film){
        filmService.save(film);
        return new ResponseEntity<>("New film was added", HttpStatus.CREATED);
    }
}
