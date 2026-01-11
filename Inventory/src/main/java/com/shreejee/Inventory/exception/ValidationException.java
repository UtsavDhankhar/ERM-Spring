package com.shreejee.Inventory.exception;

import java.util.List;

public class ValidationException extends RuntimeException{

    String code;
    String message;
    List<String> fields;

    public ValidationException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ValidationException(String code, String message, List<String> fields) {
        super();
        this.code = code;
        this.message = message;
        this.fields = fields;
    }
}
