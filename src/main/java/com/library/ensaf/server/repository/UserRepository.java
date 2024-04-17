package com.library.ensaf.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.ensaf.server.model.User;


public interface UserRepository extends MongoRepository<User,String> {

    User findByIdentifier(Integer user);




}
