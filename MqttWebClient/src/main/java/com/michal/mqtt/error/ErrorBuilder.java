package com.michal.mqtt.error;

import java.util.ArrayList;
import java.util.List;

public class ErrorBuilder {

    private String message;
    private List<String> errors = new ArrayList<>();

    public ErrorBuilder() {
    }

    public ErrorBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ErrorBuilder error(String error) {
        errors.add(error);
        return this;
    }

    public ErrorData build(){
        return new ErrorData(message, errors);
    }

}
