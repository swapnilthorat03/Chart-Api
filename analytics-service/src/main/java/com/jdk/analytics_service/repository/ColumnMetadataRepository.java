package com.jdk.analytics_service.repository;

import com.jdk.analytics_service.entity.ColumnMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ColumnMetadataRepository
        extends JpaRepository<ColumnMetadata,Long>{

    List<ColumnMetadata> findByDatasetId(Long datasetId);
}