package com.library.ensaf.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.library.ensaf.server.model.Book;

@Repository
public interface BookRepository extends MongoRepository<Book,String> {

    List<Book> findByTitleContaining(String Title);
    List<Book> findByModuleContaining(String Module);
    List<Book> findByAuthorContaining(String Author);
    Optional<Book> findByNInv(Integer id);
    List<Book> findByAvailableTrue();

}
