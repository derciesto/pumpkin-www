package com.ciesto.common.customException;

import org.springframework.http.HttpStatus;

public class CreditRequirementNotFound extends RuntimeException {

    private final int errorCode;
    private final HttpStatus status;

    public CreditRequirementNotFound(String message, int errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = HttpStatus.NOT_FOUND;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
