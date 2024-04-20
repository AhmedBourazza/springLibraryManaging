package com.library.ensaf.projet.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.ensaf.projet.model.Student;
import com.library.ensaf.projet.repository.StudentRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/Student")
public class StudentController {
    
    StudentRepository repo;


    public StudentController(StudentRepository repository) {
        this.repo = repository;
    }

    @GetMapping("/Search")
    public ResponseEntity<?> getAllBooks() {
        List<Student> res = repo.findAll();
        return new ResponseEntity<List<Student>>(res, HttpStatus.OK);
    }

}
