package com.library.ensaf.projet;

import com.library.ensaf.projet.model.Book;
import com.library.ensaf.projet.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {
	@Autowired
	BookRepository br;
	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
	Book b = new Book();
	b.setId("15b");
	b.setTitle("Java EE");
	b.setAuthor("Harry Potter");
	br.save(b);

	}
}
