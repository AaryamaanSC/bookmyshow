package com.aarya.bms.bms.merging.entity;

import com.aarya.bms.bms.merging.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Movie {
    @Id
    private String id;
    private String name;
    private int durationInMinutes;
    private String language;
    private List<MovieGenre> genre;
//    private List<String> runningShowIds;
}
