package com.michal.mqtt.engine.raport.model;

import java.util.List;

public class ReportModel {

    private String roomName;
    private List<SensorModel> sensors;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<SensorModel> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorModel> sensors) {
        this.sensors = sensors;
    }
}
