package com.michal.mqtt.engine.raport.model;

import java.util.List;

public class SensorTypeDataModel {

    private String type;
    private List<SensorDataModel> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SensorDataModel> getData() {
        return data;
    }

    public void setData(List<SensorDataModel> data) {
        this.data = data;
    }
}
