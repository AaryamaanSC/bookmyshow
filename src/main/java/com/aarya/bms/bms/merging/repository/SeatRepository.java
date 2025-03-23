package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.ShowSeat;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SeatRepository extends MongoRepository<ShowSeat,String> {
}
