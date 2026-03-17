package com.jdk.batch_service.entity;

import jakarta.persistence.*;

@Entity
public class ColumnMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long datasetId;

    private String columnName;

    private String columnType;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getDatasetId() { return datasetId; }

    public void setDatasetId(Long datasetId) { this.datasetId = datasetId; }

    public String getColumnName() { return columnName; }

    public void setColumnName(String columnName) { this.columnName = columnName; }

    public String getColumnType() { return columnType; }

    public void setColumnType(String columnType) { this.columnType = columnType; }

}