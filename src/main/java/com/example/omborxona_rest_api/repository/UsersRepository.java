package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    @Query("select u.name,u.phone,u.warehouseList,u.email from Users  u where u.id=?1")
    Optional<Users> getOneByIdJpql(Integer id);

}
