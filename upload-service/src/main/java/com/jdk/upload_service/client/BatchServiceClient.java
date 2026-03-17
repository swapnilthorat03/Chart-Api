package com.jdk.upload_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BatchServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    public void sendCsvToBatch(MultipartFile file) {

        try {

            ByteArrayResource resource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", resource);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<MultiValueMap<String, Object>> request =
                    new HttpEntity<>(body, headers);

            restTemplate.postForEntity(
                    "http://localhost:8082/batch/process",
                    request,
                    String.class
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to send CSV to batch service");
        }
    }
}