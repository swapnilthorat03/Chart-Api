package com.jdk.analytics_service.dto;

public class ChartRequest {

    private Long datasetId;
    private String xColumn;
    private String yColumn;
    private String aggregation;
    private String chartType;

    public Long getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(Long datasetId) {
        this.datasetId = datasetId;
    }

    public String getxColumn() {
        return xColumn;
    }

    public void setxColumn(String xColumn) {
        this.xColumn = xColumn;
    }

    public String getyColumn() {
        return yColumn;
    }

    public void setyColumn(String yColumn) {
        this.yColumn = yColumn;
    }

    public String getAggregation() {
        return aggregation;
    }

    public void setAggregation(String aggregation) {
        this.aggregation = aggregation;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }
}