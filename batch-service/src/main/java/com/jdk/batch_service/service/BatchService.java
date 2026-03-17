package com.jdk.batch_service.service;

import com.jdk.batch_service.entity.*;
import com.jdk.batch_service.repository.*;
import com.jdk.batch_service.reader.CsvReader;
import com.jdk.batch_service.util.ColumnTypeDetector;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class BatchService {

    @Autowired
    DatasetRepository datasetRepository;

    @Autowired
    ColumnMetadataRepository columnRepository;

    @Autowired
    DatasetRowRepository rowRepository;

    public void processCsv(MultipartFile file){

        try{

            if(file == null || file.isEmpty()){
                throw new Exception("File is empty");
            }

            Dataset dataset = new Dataset();
            dataset.setName(file.getOriginalFilename());

            dataset = datasetRepository.save(dataset);

            List<Map<String,String>> rows = CsvReader.read(file);

            if(rows.isEmpty()){
                throw new Exception("CSV contains no data");
            }

            Map<String,String> firstRow = rows.get(0);

            Map<String,String> columnTypes = new HashMap<>();

            for(String column : firstRow.keySet()){

                String type =
                        ColumnTypeDetector.detect(firstRow.get(column));

                columnTypes.put(column,type);

                ColumnMetadata meta = new ColumnMetadata();

                meta.setDatasetId(dataset.getId());
                meta.setColumnName(column);
                meta.setColumnType(type);

                columnRepository.save(meta);
            }

            ObjectMapper mapper = new ObjectMapper();

            Set<String> duplicateCheck = new HashSet<>();

            List<DatasetRow> batch = new ArrayList<>();

            int batchSize = 500;

            for(Map<String,String> rowMap : rows){

                Map<String,String> cleanedRow = new HashMap<>();

                for(String column : rowMap.keySet()){

                    String value = rowMap.get(column);

                    String type = columnTypes.get(column);

                    if(type.equals("NUMBER") &&
                            (value == null || value.isEmpty())){

                        value = "0";
                    }

                    if(value != null){
                        value = value.trim();
                    }

                    cleanedRow.put(column,value);
                }

                String key = cleanedRow.toString();

                if(duplicateCheck.contains(key)){
                    continue;
                }

                duplicateCheck.add(key);

                DatasetRow row = new DatasetRow();

                row.setDatasetId(dataset.getId());
                row.setRowData(mapper.writeValueAsString(cleanedRow));

                batch.add(row);

                if(batch.size() == batchSize){

                    rowRepository.saveAll(batch);
                    batch.clear();
                }
            }

            if(!batch.isEmpty()){
                rowRepository.saveAll(batch);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}