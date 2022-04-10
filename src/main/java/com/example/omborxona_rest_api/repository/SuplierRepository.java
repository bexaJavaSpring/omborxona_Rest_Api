package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Suplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuplierRepository extends JpaRepository<Suplier,Integer> {

    boolean existsByNameIgnoreCase(String name);
}
