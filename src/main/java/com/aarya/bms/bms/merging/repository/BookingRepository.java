package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking,String> {
}
