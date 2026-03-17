package com.jdk.batch_service.repository;

import com.jdk.batch_service.entity.ColumnMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnMetadataRepository extends JpaRepository<ColumnMetadata,Long> {
}