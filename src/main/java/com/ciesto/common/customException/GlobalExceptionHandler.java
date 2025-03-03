package com.ciesto.common.customException;


import com.ciesto.common.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProposalNotAvailable.class)
    public ResponseEntity<Map<String, Object>> handleProposalNotAvailable(ProposalNotAvailable ex) {
        return buildErrorResponse(ex.getMessage(), ex.getErrorCode(), ex.getStatus());
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
//        return buildErrorResponse("An unexpected error occurred", 50000, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(CreditRequirementNotFound.class)
    public ResponseEntity<Map<String, Object>> handleCreditRequirementNotFound(CreditRequirementNotFound ex) {
        return buildErrorResponse(ex.getMessage(), ex.getErrorCode(), ex.getStatus());
    }

    @ExceptionHandler(ImproperDataException.class)
    public ResponseEntity<Map<String, Object>> handleImproperData(ImproperDataException ex) {
        return buildErrorResponse(ex.getMessage(), ex.getErrorCode(), ex.getStatus());
    }


    private ResponseEntity<Map<String, Object>> buildErrorResponse(String message, int errorCode, HttpStatus status) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put(Constants.ERROR_CODE, errorCode);
        errorResponse.put(Constants.MESSAGE, message);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}