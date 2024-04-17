package com.library.ensaf.server;

import com.library.ensaf.server.model.Book;
import com.library.ensaf.server.repository.BookRepository;
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
