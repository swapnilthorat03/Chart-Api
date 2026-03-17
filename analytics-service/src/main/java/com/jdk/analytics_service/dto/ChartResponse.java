package com.jdk.analytics_service.dto;

import java.util.List;

public class ChartResponse {

    private String chartType;
    private List<String> labels;
    private List<Double> values;

    public ChartResponse(){}

    public ChartResponse(String chartType, List<String> labels, List<Double> values) {
        this.chartType = chartType;
        this.labels = labels;
        this.values = values;
    }

    public String getChartType() {
        return chartType;
    }

    public List<String> getLabels() {
        return labels;
    }

    public List<Double> getValues() {
        return values;
    }
}