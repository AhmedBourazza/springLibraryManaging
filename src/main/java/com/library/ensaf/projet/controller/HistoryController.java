package com.library.ensaf.projet.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import com.library.ensaf.projet.model.Book;
import com.library.ensaf.projet.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import com.library.ensaf.projet.model.History;
import com.library.ensaf.projet.model.User;
import com.library.ensaf.projet.model.UserRequest;
import com.library.ensaf.projet.repository.HistoryRepository;
import com.library.ensaf.projet.repository.UserRepository;

@Controller
@CrossOrigin
@RequestMapping("/")
public class HistoryController {

    @Autowired
    HistoryRepository repo;

    @Autowired
    UserRepository userRepo;

  @Autowired
  BookRepository bookRepo;

    @PostMapping("/api/History/save")
    public ResponseEntity<?> BorrowBook(@RequestBody UserRequest req) {
        History demand = new History();
        demand.setBook(req.getBook());
        demand.setUser(req.getUser());
        demand.setReturned(false);
        History savedHistory = repo.save(demand);
        return new ResponseEntity<>(savedHistory, HttpStatus.OK);
    }

    



    @PostMapping("/api/History/Confirm/{id}")
    public ResponseEntity<?> ConfirmBorrow(@PathVariable Integer id) {


        
        History demandTreatement = repo.findByBookAndReturned(id,false).get(0);
        
    
        Calendar calendar = Calendar.getInstance();
    
        demandTreatement.setBorrowDate(calendar.getTime());
        
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        demandTreatement.setReturnDate(calendar.getTime());
    
        History savedHistory = repo.save(demandTreatement);

        return new ResponseEntity<>(savedHistory, HttpStatus.OK);
    }

        @GetMapping("/History/Confirm/{id}")
    public String ConfirmABorrow(@PathVariable Integer id) {



        History demandTreatement = repo.findByBookAndReturned(id,false).get(0);


        Calendar calendar = Calendar.getInstance();

        demandTreatement.setBorrowDate(calendar.getTime());

        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        demandTreatement.setReturnDate(calendar.getTime());

        History savedHistory = repo.save(demandTreatement);

            Optional<Book> optionalBook = bookRepo.findByNInv(id);


            Book book = optionalBook.get();


            book.setAvailable(false);
            bookRepo.save(book);
        return "redirect:/Admin";
    }
    @GetMapping("/returnBook/{id}")
    public String returnBook(@PathVariable String id) {
        Optional<History> optionalHistory = repo.findById(id);
        if (optionalHistory.isPresent()) {
            History history = optionalHistory.get();
            // Update the history entry to mark the book as returned
            history.setReturned(true);
            Book b = bookRepo.findByNInv(history.getBook()).get();
            b.setAvailable(true);
            bookRepo.save(b);
            repo.save(history);
        }
        return "redirect:/Admin";
    }



    @DeleteMapping("/api/History/Deny/{id}")
    public ResponseEntity<?> deleteHistoryById(@PathVariable Integer id) {
    repo.deleteByBookAndReturned(id, false);
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
   

    @GetMapping("/api/History/Search")
    public ResponseEntity<?> getAllEntries() {
        List<History> res = repo.findAll();
        return new ResponseEntity<List<History>>(res, HttpStatus.OK);
    }

    @GetMapping("/Confirmed")
    public List<History> getHistoryWithoutDate() {
        return repo.findByBorrowDateIsNotNull();
    }

    @GetMapping("/Admin")
    public String getHistoryWithDate(Model model) {
List<History> Records=repo.findByBorrowDateIsNull();
List<History> RecordsBorrowed = repo.findByBorrowDateIsNotNullAndReturned(false);
List<String> usernames=new ArrayList<String>();
List<String> usernames2=new ArrayList<String>();

List<Book> books = bookRepo.findAll();

List<History> Confirmed=repo.findByBorrowDateIsNotNullAndReturned(false);


for (History history:Records){
    User user = userRepo.findByIdentifier(history.getUser());
String mail=user.getEmail();

usernames.add(mail);

}
for (History history:Confirmed){
    User user = userRepo.findByIdentifier(history.getUser());
String mail=user.getEmail();

usernames2.add(mail);

}
        int borrowedBooksCount = RecordsBorrowed.size();
        model.addAttribute("borrowedBooksCount", borrowedBooksCount);

        long totalBooks = bookRepo.count();
        model.addAttribute("totalBooks", totalBooks);

        long totalUsers = userRepo.count();

        // Ajouter le nombre total d'utilisateurs inscrits au modèle
        model.addAttribute("totalUsers", totalUsers);


        model.addAttribute("Books", Records);
model.addAttribute("Users", usernames);

model.addAttribute("Confirmed", Confirmed);
model.addAttribute("Users2", usernames2);
model.addAttribute("allbooks", books);
        return "Admin";
    }

}
