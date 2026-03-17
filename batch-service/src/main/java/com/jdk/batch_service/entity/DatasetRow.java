package com.jdk.batch_service.entity;

import jakarta.persistence.*;

@Entity
public class DatasetRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long datasetId;

    @Column(columnDefinition = "TEXT")
    private String rowData;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getDatasetId() { return datasetId; }

    public void setDatasetId(Long datasetId) { this.datasetId = datasetId; }

    public String getRowData() { return rowData; }

    public void setRowData(String rowData) { this.rowData = rowData; }

}