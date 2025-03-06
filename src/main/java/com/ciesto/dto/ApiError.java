package com.ciesto.dto;

public class ApiError {
    private String error;
    private String message;

    public ApiError(String error, String message) {
        this.error = error;
        this.message = message;
    }

    // Getters & Setters
    public String getError() { return error; }
    public String getMessage() { return message; }
}
