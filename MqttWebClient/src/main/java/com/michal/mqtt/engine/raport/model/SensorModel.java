package com.michal.mqtt.engine.raport.model;

import java.util.List;

public class SensorModel {

    private String sensorName;
    private String nodeName;
    private List<SensorTypeDataModel> sensorTypeDatumModels;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<SensorTypeDataModel> getSensorTypeDatumModels() {
        return sensorTypeDatumModels;
    }

    public void setSensorTypeDatumModels(List<SensorTypeDataModel> sensorTypeDatumModels) {
        this.sensorTypeDatumModels = sensorTypeDatumModels;
    }
}
