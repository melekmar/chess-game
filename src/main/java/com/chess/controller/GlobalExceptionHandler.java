package com.chess.controller;

import com.chess.model.MoveValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for REST controllers.
 * Catches specific exceptions and returns meaningful HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles MoveValidationException thrown during piece movement.
     * @param ex the exception instance
     * @return a response entity with error details and 400 Bad Request status
     */
    @ExceptionHandler(MoveValidationException.class)
    public ResponseEntity<Map<String, String>> handleMoveValidationException(MoveValidationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "error"); // Indicates the result is an error
        response.put("message", ex.getMessage()); // Provides the specific error message
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Returns HTTP 400 status
    }
}

