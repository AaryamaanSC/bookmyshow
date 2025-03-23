package com.aarya.bms.bms.merging.repository;

import com.aarya.bms.bms.merging.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
}
