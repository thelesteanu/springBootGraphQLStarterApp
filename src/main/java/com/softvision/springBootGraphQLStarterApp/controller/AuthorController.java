package com.softvision.springBootGraphQLStarterApp.controller;

import com.softvision.springBootGraphQLStarterApp.entity.Author;
import com.softvision.springBootGraphQLStarterApp.entity.Book;
import com.softvision.springBootGraphQLStarterApp.repository.AuthorRepository;
import com.softvision.springBootGraphQLStarterApp.repository.BookRepository;
import lombok.extern.java.Log;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
        log.info("!! Retrieving bestseller number for Author's name: " + author.getName() + " !!");
        Random random = new Random();
        return random.nextInt(10);
    }

//    @BatchMapping
//    public Map<Author, Integer> bestSellerNumber(List<Author> authorList) {
//        Random random = new Random();
//        log.info("!! Running batch command !! ");
//        return authorList.stream().collect(Collectors.toMap(author -> author, author -> {
//            return random.nextInt(10);
//        }));
//    }






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
