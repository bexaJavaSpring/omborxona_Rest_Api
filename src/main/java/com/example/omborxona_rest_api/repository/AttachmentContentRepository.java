package com.example.omborxona_rest_api.repository;

import com.example.omborxona_rest_api.entity.Attachment;
import com.example.omborxona_rest_api.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    Optional<AttachmentContent> findByAttachment(Attachment attachment);


}
