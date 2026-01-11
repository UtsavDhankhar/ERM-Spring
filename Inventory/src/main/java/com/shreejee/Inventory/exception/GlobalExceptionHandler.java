package com.shreejee.Inventory.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidation(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.message);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleUnknownException(RuntimeException ex) {
         return ResponseEntity.badRequest().body("Unknown Exception");
    }

}
