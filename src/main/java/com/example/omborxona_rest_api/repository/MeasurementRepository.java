package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {

    @Query("select m.name from Measurement  m where m.id=?1")
    Optional<Measurement> getOneByIdJpql(Integer id);

    boolean existsByNameIgnoreCase(String name);
}
