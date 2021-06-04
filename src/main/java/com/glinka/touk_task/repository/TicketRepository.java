package com.glinka.touk_task.repository;

import com.glinka.touk_task.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
