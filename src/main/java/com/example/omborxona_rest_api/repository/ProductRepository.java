package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findAllByNameContainingIgnoreCase(String name);

    @Query("select p.name,p.attachment.name,p.category.name,p.measurement.name from Product  p where p.id=?1")
    Optional<Product> getOneByIdJpql(Integer id);

    boolean existsByNameIgnoreCase(String name);
}
