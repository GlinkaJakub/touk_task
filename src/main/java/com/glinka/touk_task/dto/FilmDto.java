package com.glinka.touk_task.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FilmDto {

    private String title;
    private String date;
    private String time;
    private int roomId;
}
