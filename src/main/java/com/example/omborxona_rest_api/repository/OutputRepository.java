package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OutputRepository extends JpaRepository<Output,Integer> {

    @Query("select o.facture_number,o.date,o.warehouse.name,o.currency.name from Output  o where o.id=?1")
    Optional<Output> getOneByIdJpql(Integer id);

//
//    List<Output>findAllByDateBetween(Date from , Date to);

//    Page<Output> findAllByCreatedAtBeet(PageRequest of, Timestamp from, Timestamp to);

   // Page<Output> kimdir(Timestamp from, Timestamp to);

   // Slice<Output> nimadir(Pageable pageable,Timestamp begin,Timestamp end);

    @Query(value = "select * from output where id=:id", nativeQuery = true)
    Optional<Output> getOneByIdNative(Integer id);
}
