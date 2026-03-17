package com.jdk.batch_service.repository;

import com.jdk.batch_service.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset,Long> {
}