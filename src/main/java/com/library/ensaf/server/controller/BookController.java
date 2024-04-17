package com.library.ensaf.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

import com.library.ensaf.server.model.Book;

import com.library.ensaf.server.repository.BookRepository;

@Controller
@CrossOrigin
@RequestMapping("/")
public class BookController {

 
    @Autowired
    BookRepository repo;

    
 
    @GetMapping("/Client")
    public String getAllBooks(Model model) {
        List<Book> res = repo.findByAvailableTrue();
        model.addAttribute("Books", res);

        return "espaceClient";
    }

    @GetMapping("/Client/SearchByTitle")
    public String searchByTitle(@RequestParam String Title,Model model) {//
        List<Book> books = repo.findByTitleContaining(Title);
        model.addAttribute("Books", books);
        return "espaceClient";
    }

    @GetMapping("/SearchByModule")
    public ResponseEntity<List<Book>> searchByModule(@RequestParam String Module) {//
        List<Book> books = repo.findByModuleContaining(Module);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("/SearchByAuthor")
    public ResponseEntity<List<Book>> searchByAuthor(@RequestParam String Author) {//
        List<Book> books = repo.findByAuthorContaining(Author);
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addBooks(@RequestBody List<Book> books) {
        List<Book> savedBooks = repo.saveAll(books);
        return new ResponseEntity<>(savedBooks, HttpStatus.OK);
    }




    @PostMapping("/api/Books/{id}/updateAvailable")
    public ResponseEntity<String> updateAvailability(@PathVariable Integer id) {
        Optional<Book> optionalBook = repo.findByNInv(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAvailable(!book.getAvailable());
            repo.save(book);
            return ResponseEntity.ok("Available field updated successfully for book with ID: " + id);
        } else {
            return ResponseEntity.notFound().build(); // Book with given ID not found
        }
    }

}
