package com.glinka.touk_task.dto;

import com.glinka.touk_task.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequestDto {

    private int filmId;
    private int seatId;
    private String name;
    private String surname;
    private TicketType type;

}
