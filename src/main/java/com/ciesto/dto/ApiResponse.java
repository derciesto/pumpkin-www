package com.ciesto.dto;

public class ApiResponse<T> {
    private boolean success;
    private T data;
    private ApiError error;

    public ApiResponse(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static <T> ApiResponse<T> error(String errorMessage) {
        return new ApiResponse<>(false, null, new ApiError(errorMessage, errorMessage));
    }

    // Getters & Setters
    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public ApiError getError() { return error; }
}