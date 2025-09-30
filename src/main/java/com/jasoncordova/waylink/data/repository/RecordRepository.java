package com.jasoncordova.waylink.data.repository;

import com.jasoncordova.waylink.data.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<RecordEntity, UUID> {
    List<RecordEntity> findByUrlId(UUID urlId);
}
