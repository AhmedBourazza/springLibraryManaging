package com.library.ensaf.projet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.ensaf.projet.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

    List<Book> findByTitleContaining(String Title);
    List<Book> findByModuleContaining(String Module);
    List<Book> findByAuthorContaining(String Author);
    Optional<Book> findByNInv(Integer id);
    List<Book> findByAvailableTrue();

}
