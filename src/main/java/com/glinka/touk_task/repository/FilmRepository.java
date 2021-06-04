package com.glinka.touk_task.repository;

import com.glinka.touk_task.model.Film;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    List<Film> findAllByDateAndTime(LocalDate date, LocalTime time, Sort sort);
    List<Film> findAllByDateBetweenAndTimeBetween(LocalDate dateStart, LocalDate dateEnd, LocalTime timeStart, LocalTime timeEnd, Sort sort);

}
