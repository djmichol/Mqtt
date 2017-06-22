package com.michal.mqtt.rest.model;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

public class ErrorData {
    private final int status;
    private final String message;
    private List<String> errors = new ArrayList<>();

    public ErrorData(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public void addFieldError(String message) {
        errors.add(message);
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
