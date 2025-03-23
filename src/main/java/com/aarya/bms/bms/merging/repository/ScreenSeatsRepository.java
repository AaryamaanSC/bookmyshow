package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.ScreenSeats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenSeatsRepository extends MongoRepository<ScreenSeats,String> {
}
