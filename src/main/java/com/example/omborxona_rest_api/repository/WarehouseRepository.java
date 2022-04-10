package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {

    @Query("select w.name from Warehouse  w where w.id=?1")
    Optional<Warehouse> getOneByIdJpql(Integer id);

    boolean existsByNameIgnoreCase(String name);

}
