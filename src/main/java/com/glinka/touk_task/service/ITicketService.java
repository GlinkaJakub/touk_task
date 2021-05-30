package com.glinka.touk_task.service;

import com.glinka.touk_task.dto.TicketRequestDto;
import com.glinka.touk_task.dto.TicketResponseDto;
import com.glinka.touk_task.model.Ticket;
import com.glinka.touk_task.model.TicketType;

public interface ITicketService {

    TicketResponseDto save(TicketRequestDto ticketRequestDto);

}
