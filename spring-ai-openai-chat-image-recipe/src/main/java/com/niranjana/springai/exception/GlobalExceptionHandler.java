package com.niranjana.springai.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.niranjana.springai.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AiServiceException.class)
    public ResponseEntity<ErrorResponse> handleAiException(AiServiceException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("AI_ERROR", ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return new ResponseEntity<>(
                new ErrorResponse("GENERIC_ERROR", "Something went wrong"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}