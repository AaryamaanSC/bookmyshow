package com.aarya.bms.bms.merging.controller;


import com.aarya.bms.bms.merging.dto.MovieDto;
import com.aarya.bms.bms.merging.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.createMovie(movieDto));
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable String id, @RequestBody MovieDto movieDto) {
        return ResponseEntity.ok(movieService.updateMovie(id, movieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
