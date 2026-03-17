package com.jdk.batch_service.util;

public class ColumnTypeDetector {

    public static String detect(String value){

        if(value == null || value.trim().isEmpty())
            return "STRING";

        if(value.matches("\\d{4}-\\d{2}-\\d{2}"))
            return "DATE";

        if(value.matches("-?\\d+(\\.\\d+)?"))
            return "NUMBER";

        return "STRING";
    }
}