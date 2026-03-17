package com.jdk.analytics_service.repository;

import com.jdk.analytics_service.entity.DatasetRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DatasetRowRepository extends JpaRepository<DatasetRow, Long> {

    List<DatasetRow> findByDatasetId(Long datasetId);

}