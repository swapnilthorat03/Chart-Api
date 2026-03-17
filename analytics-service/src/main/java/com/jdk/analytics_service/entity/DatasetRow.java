package com.jdk.analytics_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dataset_row")
public class DatasetRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long datasetId;

    @Column(columnDefinition = "TEXT")
    private String rowData;

    public Long getId() {
        return id;
    }

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public String getRowData() {
        return rowData;
    }

    public void setRowData(String rowData) {
        this.rowData = rowData;
    }
}