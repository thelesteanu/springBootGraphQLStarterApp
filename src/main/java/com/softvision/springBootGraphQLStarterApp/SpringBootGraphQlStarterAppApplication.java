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

            bookRepository.saveAll(List.of(
                    new Book(null, "Reactive Spring", "Penguin", mark),
                    new Book(null, "Dancing in the moonlight", "O'Reilly", mark),
                    new Book(null, "Eternal spotless", "Penguin", andi)));
        };
    }
}
