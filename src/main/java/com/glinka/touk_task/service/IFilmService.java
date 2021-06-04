package com.glinka.touk_task.service;

import com.glinka.touk_task.dto.FilmDto;
import com.glinka.touk_task.dto.FilmInformationDto;
import com.glinka.touk_task.model.Film;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IFilmService {

    List<Film> findAllByDateAndTime(LocalDate parse, LocalTime parse1);

    List<Film> findAllByDateBetweenAndTimeBetween(LocalDate parse, LocalDate parse1, LocalTime parse2, LocalTime parse3);

    FilmInformationDto findFilmInformationById(int id);
    Film findById(int id);

    void save(FilmDto film);

    Film mapToDao(FilmDto filmDto);
    FilmDto mapToDto(Film film);
    FilmInformationDto mapToFilmInformationDto(Film film);
}
