package com.michal.mqtt.error;

import java.util.List;

public class ErrorData {
    private final String message;
    private List<String> errors;

    public ErrorData(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }
}
