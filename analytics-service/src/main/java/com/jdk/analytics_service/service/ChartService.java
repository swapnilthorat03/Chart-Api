package com.jdk.analytics_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdk.analytics_service.dto.ChartRequest;
import com.jdk.analytics_service.dto.ChartResponse;
import com.jdk.analytics_service.entity.DatasetRow;
import com.jdk.analytics_service.entity.ColumnMetadata;
import com.jdk.analytics_service.repository.DatasetRowRepository;
import com.jdk.analytics_service.repository.ColumnMetadataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChartService {

    @Autowired
    DatasetRowRepository repository;

    @Autowired
    ColumnMetadataRepository columnRepository;

    ObjectMapper mapper = new ObjectMapper();

    // 🔹 Generate chart data
    public ChartResponse generateChart(ChartRequest request){

        List<DatasetRow> rows =
                repository.findByDatasetId(request.getDatasetId());

        Map<String,List<Double>> groupedData = new LinkedHashMap<>();

        try{

            for(DatasetRow row : rows){

                Map<String,String> data =
                        mapper.readValue(row.getRowData(), Map.class);

                String xValue = data.get(request.getxColumn());

                if(xValue == null || xValue.isEmpty()){
                    xValue = "Unknown";
                }

                double yValue = 0;

                String y = data.get(request.getyColumn());

                if(y != null && !y.isEmpty()){
                    try{
                        yValue = Double.parseDouble(y);
                    }catch(Exception e){
                        yValue = 0;
                    }
                }

                groupedData
                        .computeIfAbsent(xValue,k->new ArrayList<>())
                        .add(yValue);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        Map<String,Double> aggregatedResult = new LinkedHashMap<>();

        for(String key : groupedData.keySet()){

            List<Double> values = groupedData.get(key);

            double result = 0;

            switch(request.getAggregation()){

                case "SUM":
                    result = values.stream().mapToDouble(Double::doubleValue).sum();
                    break;

                case "AVG":
                    result = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
                    break;

                case "COUNT":
                    result = values.size();
                    break;

                case "MAX":
                    result = values.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                    break;

                case "MIN":
                    result = values.stream().mapToDouble(Double::doubleValue).min().orElse(0);
                    break;

                default:
                    result = values.stream().mapToDouble(Double::doubleValue).sum();
            }

            aggregatedResult.put(key,result);
        }

        List<String> labels = new ArrayList<>(aggregatedResult.keySet());
        List<Double> values = new ArrayList<>(aggregatedResult.values());

        return new ChartResponse(
                request.getChartType(),
                labels,
                values
        );
    }

    // 🔹 Get dataset columns (for frontend dropdown)
    public List<String> getColumns(Long datasetId){

        List<ColumnMetadata> columns =
                columnRepository.findByDatasetId(datasetId);

        List<String> columnNames = new ArrayList<>();

        for(ColumnMetadata column : columns){
            columnNames.add(column.getColumnName());
        }

        return columnNames;
    }
    
    
    public List<ChartResponse> generateDashboard(List<ChartRequest> requests){

        List<ChartResponse> responses = new ArrayList<>();

        for(ChartRequest req : requests){

            ChartResponse response = generateChart(req);

            responses.add(response);
        }

        return responses;
    }
}