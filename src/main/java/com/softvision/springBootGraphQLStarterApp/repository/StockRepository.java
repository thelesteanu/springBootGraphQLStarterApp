package com.softvision.springBootGraphQLStarterApp.repository;

import com.softvision.springBootGraphQLStarterApp.entity.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;


@GraphQlRepository
public interface StockRepository extends CrudRepository<Stock, Long>/*, QuerydslPredicateExecutor<Stock>*/ {

}
