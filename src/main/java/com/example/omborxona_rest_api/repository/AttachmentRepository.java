package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

    @Query("select a.name,a.size,a.type from Attachment  a where a.id=?1")
    Optional<Attachment> getOneByIdJpql(Integer id);
}
