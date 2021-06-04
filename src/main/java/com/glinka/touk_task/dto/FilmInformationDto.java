package com.glinka.touk_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmInformationDto {

    private int id;
    private String title;
    private String date;
    private String time;
    private String roomName;
    private List<SeatDto> seats;
}
