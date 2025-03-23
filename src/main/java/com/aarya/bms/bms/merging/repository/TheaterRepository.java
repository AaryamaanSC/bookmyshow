package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.Theater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends MongoRepository<Theater,String> {
}
