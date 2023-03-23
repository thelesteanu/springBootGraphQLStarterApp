package com.softvision.springBootGraphQLStarterApp.repository;

import com.softvision.springBootGraphQLStarterApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
