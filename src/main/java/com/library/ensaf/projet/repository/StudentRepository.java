package com.library.ensaf.projet.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.library.ensaf.projet.model.Student;

public interface StudentRepository extends MongoRepository<Student,String> {
    
}
