package com.jdk.upload_service.service;

import com.jdk.upload_service.client.BatchServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    @Autowired
    private BatchServiceClient batchServiceClient;

    public String sendFileToBatchService(MultipartFile file) {

        batchServiceClient.sendCsvToBatch(file);

        return "CSV file sent to batch service successfully";

    }
}