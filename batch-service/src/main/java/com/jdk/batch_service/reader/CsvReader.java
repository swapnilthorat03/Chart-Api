package com.jdk.batch_service.reader;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CsvReader {

    public static List<Map<String,String>> read(MultipartFile file) throws Exception {

        List<Map<String,String>> rows = new ArrayList<>();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(file.getInputStream()));

        String headerLine = reader.readLine();

        if(headerLine == null || headerLine.trim().isEmpty()){
            return rows;
        }

        String[] headers = headerLine.split(",");

        String line;

        while((line = reader.readLine()) != null){

            if(line.trim().isEmpty())
                continue;

            String[] values = line.split(",");

            Map<String,String> row = new HashMap<>();

            for(int i=0;i<headers.length;i++){

                String value = "";

                if(i < values.length)
                    value = values[i];

                value = value.trim();

                row.put(headers[i].trim(), value);
            }

            rows.add(row);
        }

        reader.close();

        return rows;
    }
}