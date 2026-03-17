package com.jdk.batch_service.repository;

import com.jdk.batch_service.entity.DatasetRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRowRepository extends JpaRepository<DatasetRow,Long> {
}