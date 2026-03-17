package com.jdk.batch_service.processor;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CsvProcessor {

    public Map<String,String> process(Map<String,String> item){

        Map<String,String> cleaned = new HashMap<>();

        for(String key : item.keySet()){

            String value = item.get(key);

            if(value == null || value.trim().isEmpty())
                value = "0";

            value = value.trim().toLowerCase();

            cleaned.put(key,value);
        }

        return cleaned;
    }
}