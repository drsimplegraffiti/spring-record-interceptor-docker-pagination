package com.abcode.springrefresh.exception;

import com.abcode.springrefresh.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ConflictException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}