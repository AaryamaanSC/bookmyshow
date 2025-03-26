package com.aarya.bms.bms.merging.service;

import com.aarya.bms.bms.merging.dto.MovieDto;
import com.aarya.bms.bms.merging.entity.Movie;
import com.aarya.bms.bms.merging.exceptions.MovieNotFoundException;
import com.aarya.bms.bms.merging.indexes.MovieIndex;
import com.aarya.bms.bms.merging.persistance.IPersistence;
import com.aarya.bms.bms.merging.persistance.MoviePersistence;
import com.aarya.bms.bms.merging.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MoviePersistence moviePersistence;
    private final ModelMapper modelMapper;

    @CacheEvict(value = "movies", allEntries = true)
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie savedMovie = moviePersistence.save(movie);
        return modelMapper.map(savedMovie, MovieDto.class);
    }

    @Cacheable(value = "movies")
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
    }

    @Cacheable(value = "movies", key = "#id")
    public MovieDto getMovieById(String id) {
        return movieRepository.findById(id)
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @CacheEvict(value = "movies", key = "#id")
    public MovieDto updateMovie(String id, MovieDto movieDto) {
        if (!movieRepository.existsById(id)) throw new MovieNotFoundException(id);

        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie.setId(id);
        Movie updatedMovie = movieRepository.save(movie);
        return modelMapper.map(updatedMovie, MovieDto.class);
    }

    @CacheEvict(value = "movies", key = "#id", allEntries = true)
    public void deleteMovie(String id) {
        if (!movieRepository.existsById(id)) throw new MovieNotFoundException(id);
        moviePersistence.deleteById(id);
    }

}
