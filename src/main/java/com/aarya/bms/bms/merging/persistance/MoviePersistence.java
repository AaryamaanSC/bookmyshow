package com.aarya.bms.bms.merging.persistance;

import com.aarya.bms.bms.merging.entity.Movie;
import com.aarya.bms.bms.merging.indexes.MovieIndex;
import com.aarya.bms.bms.merging.repository.MovieElasticRepository;
import com.aarya.bms.bms.merging.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class MoviePersistence implements IPersistence<Movie> {

    private final MovieRepository movieMongoRepository;
    private final MovieElasticRepository movieElasticRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public MoviePersistence(MovieRepository movieMongoRepository, MovieElasticRepository movieElasticRepository) {
        this.movieMongoRepository = movieMongoRepository;
        this.movieElasticRepository = movieElasticRepository;
    }

    @Override
    public Movie save(Movie movie){
        Movie movieObject = movieMongoRepository.save(movie);

        MovieIndex movieIndex = modelMapper.map(movieObject, MovieIndex.class);
        movieElasticRepository.save(movieIndex);
        return movieObject;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return movieMongoRepository.findById(id);
    }

    @Override
    public void deleteById(String id){
        movieMongoRepository.deleteById(id);
        movieElasticRepository.deleteById(id);
    }
}