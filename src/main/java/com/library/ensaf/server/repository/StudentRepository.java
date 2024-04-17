package com.library.ensaf.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.ensaf.server.model.Student;

public interface StudentRepository extends MongoRepository<Student,String> {
    
}
