package com.jdk.upload_service.controller;

import com.jdk.upload_service.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/csv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {

        String response = uploadService.sendFileToBatchService(file);

        return ResponseEntity.ok(response);
    }
}