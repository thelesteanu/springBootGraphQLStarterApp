package com.softvision.springBootGraphQLStarterApp;

import com.softvision.springBootGraphQLStarterApp.entity.Author;
import com.softvision.springBootGraphQLStarterApp.entity.Book;
import com.softvision.springBootGraphQLStarterApp.repository.AuthorRepository;
import com.softvision.springBootGraphQLStarterApp.repository.BookRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootGraphQlStarterAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraphQlStarterAppApplication.class, args);
    }


    @Bean
    ApplicationRunner applicationRunner(AuthorRepository authorRepository, BookRepository bookRepository) {
        return args -> {
            Author mark = authorRepository.save(new Author(null, "Mark Montagne"));
            Author andi = authorRepository.save(new Author(null, "Andi Flaqui"));

            Book book1 = new Book(null, "Reactive Spring", "Penguin", mark);
            Book book2 = new Book(null, "Dancing in the moonlight", "O'Reilly", mark);
            Book book3 = new Book(null, "Eternal spotless", "Penguin", andi);
            bookRepository.saveAll(List.of(
                    book1,
                    book2,
                    book3));
        };
    }
}
