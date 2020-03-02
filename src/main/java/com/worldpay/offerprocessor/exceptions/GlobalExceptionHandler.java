package com.worldpay.offerprocessor.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.exc.ValueInstantiationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * GlobalExceptionHandler handles
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles failed Spring json body to bean mappings during instantiation
     * 
     * @return the error in the form on JSON
     */
    @ExceptionHandler(value = { ValueInstantiationException.class })
    protected ResponseEntity<Map<String, String>> handleInvalidRequest(RuntimeException ex, WebRequest request) {

        Map<String, String> body = new HashMap<>();

        body.put("message", "Invalid request body. Please check your payload is properly formatted.");
        body.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
        body.put("error", HttpStatus.BAD_REQUEST.name());
        body.put("timestamp", new Date().toInstant().toString());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles invalid field values passed in API
     */
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}