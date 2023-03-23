package com.softvision.springBootGraphQLStarterApp.controller;

import com.softvision.springBootGraphQLStarterApp.entity.Author;
import com.softvision.springBootGraphQLStarterApp.entity.Book;
import com.softvision.springBootGraphQLStarterApp.repository.AuthorRepository;
import com.softvision.springBootGraphQLStarterApp.repository.BookRepository;
import lombok.extern.java.Log;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Random;

@Controller
@Log
public class AuthorController {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Optional<Author> authorById(@Argument Long id) {
        return authorRepository.findById(id);
    }








    //////////// EXTEND QUERIES for custom data provider
    @SchemaMapping
    public Integer bestSellerNumber(Author author) {
        log.info("Author's name: " + author.getName());
        Random random = new Random();
        return random.nextInt(10);
    }









    //////////// MUTATIONS
    @MutationMapping
    String sayHi(@Argument String name) {
        return "Hello " + name;
    }

    @MutationMapping
    Book addBook(@Argument BookInput book) {
        Author author = authorRepository.findById(book.authorId()).orElseThrow(() -> new IllegalArgumentException("Could not find author"));
        Book newBook = new Book(book.title(), book.publisher(), author);
        return bookRepository.save(newBook);
    }

    record BookInput(String title, String publisher, Long authorId) {
    }

}
