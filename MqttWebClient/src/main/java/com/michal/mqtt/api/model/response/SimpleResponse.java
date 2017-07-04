package com.michal.mqtt.api.model.response;

import java.io.Serializable;

public class SimpleResponse implements Serializable{

    private String data;
    private String message;

    public SimpleResponse() {
    }

    private SimpleResponse(String data, String message) {
        this.data = data;
        this.message = message;
    }

    public static SimpleResponse create(String data, String message) {
        return new SimpleResponse(data, message);
    }

    public static SimpleResponse create(String message) {
        return new SimpleResponse(null, message);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
