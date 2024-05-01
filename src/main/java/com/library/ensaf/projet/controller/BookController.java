package com.library.ensaf.projet.controller;

import java.util.List;
import java.util.Optional;

import com.library.ensaf.projet.model.History;
import com.library.ensaf.projet.model.User;
import com.library.ensaf.projet.repository.HistoryRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RestController;

import com.library.ensaf.projet.model.Book;

import com.library.ensaf.projet.repository.BookRepository;

@Controller
@CrossOrigin
@RequestMapping("/")
public class BookController {

 
    @Autowired
    BookRepository repo;
    @Autowired
    HistoryRepository historyRepo;



    @GetMapping("/Client")
    public String getAllBooks(Model model, HttpSession session) {
        // Check if a user is logged in
        if (session.getAttribute("user") == null) {
            // If no user is logged in, redirect to the login page
            return "redirect:/sign";
        }

        // If a user is logged in, proceed with fetching books
        List<Book> res = repo.findByAvailableTrue();
        model.addAttribute("Books", res);
        return "espaceClient";
    }


    @GetMapping("Client/SearchByTitle")
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
    @GetMapping("/borrowBook/{id}")
    public String borrowAbook(@PathVariable String id, HttpSession session) {
        // Retrieve the user's ID from the session
        User user = (User) session.getAttribute("user");
        int userId = user.getIdentifier();

        Optional<Book> optionalBook = repo.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setAvailable(false);
            repo.save(book);

            History history = new History();
            // Set the user's ID obtained from the session
            history.setUser(userId);
            history.setBook(book.getNInv());
            history.setReturned(false);
            historyRepo.save(history);
        }

        return "redirect:/Client";
    }

    @PostMapping("/addAbook")
    public String addAbook(@ModelAttribute Book book) {
        // Save the book to the database
        book.setAvailable(true);

        repo.save(book);
        // Redirect to a success page or another appropriate page
        return "redirect:/Admin"; // Assuming you have a page to display all books
    }

    @PostMapping("/")
    public ResponseEntity<?> addBooks(@RequestBody List<Book> books) {
        List<Book> savedBooks = repo.saveAll(books);
        return new ResponseEntity<>(savedBooks, HttpStatus.OK);
    }
    @GetMapping("/delete/{NInv}")
    public String deleteAbook(@PathVariable("NInv") Integer NInv) {
        // Find the book by its ID
        Book bookToDelete = repo.findByNInv(NInv).orElse(null);

        // Check if the book exists
        if (bookToDelete != null) {
            // Delete the book from the repository
            repo.delete(bookToDelete);
            // Redirect to a success page or another appropriate page
            return "redirect:/Admin"; // Assuming you have a page to display all books
        } else {
            // Handle the case where the book with the given ID does not exist
            // Redirect to an error page or another appropriate page
            return "redirect:/Admin"; // Redirecting to the same page for simplicity
        }
    }

    @GetMapping("/update/{NInv}")
    public String updateAbook(@PathVariable("NInv") Integer NInv) {


        return "redirect:/Admin"; // Assuming you have a page to display all books
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
