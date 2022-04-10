package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    @Query("select c.name,c.phone from Client  c where c.id=?1")
    Optional<Client> getOneByIdJpql(Integer id);

    boolean existsByNameIgnoreCase(String name);

}
