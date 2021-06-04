package com.glinka.touk_task.repository;

import com.glinka.touk_task.model.Room;
import com.glinka.touk_task.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    @Query("select s from Seat s where s.rooms.id = ?1 and s.isAvailable = ?2")
    List<Seat> findAllByRoomsAndAndAvailable(int room, boolean available);
}
