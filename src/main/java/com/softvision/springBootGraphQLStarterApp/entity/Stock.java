package com.softvision.springBootGraphQLStarterApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue
    private Long id;

    private int number;

    private double price;

    @OneToOne
    Book book;


}
