package com.michal.mqtt.engine.raport.model;

import java.io.Serializable;
import java.util.Date;

public class SensorDataModel implements Serializable{

    private Date date;
    private String data;

    public static SensorDataModel of(Date date, String data) {
        return new SensorDataModel(date, data);
    }

    private SensorDataModel(Date date, String data) {
        this.date = date;
        this.data = data;
    }

    public SensorDataModel() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
