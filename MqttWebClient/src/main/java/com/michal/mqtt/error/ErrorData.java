package com.michal.mqtt.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorData {
    private final int status;
    private final String message;
    private List<String> errors;

    public ErrorData(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
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
