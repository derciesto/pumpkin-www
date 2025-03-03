package com.ciesto.common.customException;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {

    private final Long errorCode;
    private final HttpStatus status;

    public ResourceNotFoundException(String message, Long errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.status = HttpStatus.NOT_FOUND;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
