package com.library.ensaf.projet.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.ensaf.projet.model.History;

@Repository
public interface HistoryRepository extends MongoRepository<History,String> {

    List<History> findByBorrowDateIsNull();

 
    List<History> findByBorrowDateIsNotNull();

    List<History> findByBookAndReturned(Integer book, boolean returned);
    List<History> deleteByBookAndReturned(Integer book, boolean returned);


    List<History> findByBorrowDateIsNotNullAndReturned(boolean returned);
}
