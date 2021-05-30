package com.glinka.touk_task.service;

import com.glinka.touk_task.model.Room;
import com.glinka.touk_task.model.Seat;
import com.glinka.touk_task.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findById(int id) {
        return roomRepository.getById(id);
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);
    }
}
