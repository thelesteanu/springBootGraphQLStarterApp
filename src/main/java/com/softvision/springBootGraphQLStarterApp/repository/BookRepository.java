package com.softvision.springBootGraphQLStarterApp.repository;

import com.softvision.springBootGraphQLStarterApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface BookRepository extends JpaRepository<Book, Long> {
}
