package com.jasoncordova.waylink.data.repository;

import com.jasoncordova.waylink.data.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UrlRepository extends JpaRepository<UrlEntity, UUID> {
    Optional<UrlEntity> findByShortUrl(String shortUrl);
}
