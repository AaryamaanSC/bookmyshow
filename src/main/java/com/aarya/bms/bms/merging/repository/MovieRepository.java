package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie,String> {

}
