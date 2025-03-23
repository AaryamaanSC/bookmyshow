package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.Show;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends MongoRepository<Show,String> {
}
