package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAllByNameContainingIgnoreCase(String name);

    @Query("select c.name,c.active from Category  c where c.id=?1")
    Optional<Category> getOneByIdJpql(Integer id);

    Optional<Category> findCategoryByName(String name);

    boolean existsByNameIgnoreCase(String name);


}
