package com.ciesto.common.customException;

import org.springframework.http.HttpStatus;

public class ImproperDataException extends RuntimeException {

    private final int errorCode;
    private final HttpStatus status;

    public ImproperDataException(String message, int errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
