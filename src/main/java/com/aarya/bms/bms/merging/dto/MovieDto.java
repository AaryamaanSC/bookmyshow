package com.aarya.bms.bms.merging.dto;

import com.aarya.bms.bms.merging.enums.MovieGenre;
import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private String id;
    private String name;
    private int durationInMinutes;
    private String language;
    private List<MovieGenre> genre;
}
