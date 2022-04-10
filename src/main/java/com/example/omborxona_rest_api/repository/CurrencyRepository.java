package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency,Integer>{
        @Query("select c.name from Currency  c where c.id=?1")
        Optional<Currency> getOneByIdJpql(Integer id);

        boolean existsByNameIgnoreCase(String name);
}
