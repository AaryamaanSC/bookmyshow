package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.Screen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends MongoRepository<Screen,String> {
}
