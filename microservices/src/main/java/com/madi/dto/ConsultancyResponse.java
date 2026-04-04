package com.madi.dto;

public class ConsultancyResponse {
    private String message;
    private String status;

    public ConsultancyResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    // Getters and Setters are required for Jackson to convert to JSON
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
