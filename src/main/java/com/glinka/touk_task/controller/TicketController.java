package com.glinka.touk_task.controller;

import com.glinka.touk_task.dto.TicketRequestDto;
import com.glinka.touk_task.dto.TicketResponseDto;
import com.glinka.touk_task.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TicketResponseDto> saveNewTicket(@RequestBody TicketRequestDto requestDto) {
        return new ResponseEntity<>(ticketService.save(requestDto), HttpStatus.CREATED);
    }

}
