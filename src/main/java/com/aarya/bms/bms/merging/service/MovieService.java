package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.MovieDto;
import com.aarya.bms.bms.merging.entity.Movie;
import com.aarya.bms.bms.merging.exceptions.MovieNotFoundException;
import com.aarya.bms.bms.merging.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;


    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie savedMovie = movieRepository.save(movie);
        return modelMapper.map(savedMovie, MovieDto.class);
    }

    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    public MovieDto getMovieById(String id) {
        return movieRepository.findById(id)
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    public MovieDto updateMovie(String id, MovieDto movieDto) {
        if (!movieRepository.existsById(id)) throw new MovieNotFoundException(id);

        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie.setId(id);
        Movie updatedMovie = movieRepository.save(movie);
        return modelMapper.map(updatedMovie, MovieDto.class);
    }

    public void deleteMovie(String id) {
        if (!movieRepository.existsById(id)) throw new MovieNotFoundException(id);
        movieRepository.deleteById(id);
    }


}
