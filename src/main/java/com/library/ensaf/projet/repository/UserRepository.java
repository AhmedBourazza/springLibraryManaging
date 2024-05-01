package com.library.ensaf.projet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.ensaf.projet.model.User;


public interface UserRepository extends MongoRepository<User,String> {

    User findByIdentifier(Integer user);


    User findByEmailAndPassword(String email, String password);
}
