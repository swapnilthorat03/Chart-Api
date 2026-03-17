package com.jdk.upload_service.dto;

public class UploadResponse {

    private String message;

    public UploadResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}