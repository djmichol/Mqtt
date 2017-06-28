package com.michal.mqtt.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Sensor data")
public class SensorDataResponseModel implements Serializable {

    public SensorDataResponseModel() {
    }

    @ApiModelProperty(value = "place")
    private String dataPlace;
    @ApiModelProperty(value = "type")
    private String dataType;
    @ApiModelProperty(value = "room")
    private String dataRoom;
    @ApiModelProperty(value = "data")
    private String sensorData;
    @ApiModelProperty(value = "topic")
    private String topic;
    @ApiModelProperty(value = "timeStamp")
    private Date dataTimestamp;

    public String getDataPlace() {
        return dataPlace;
    }

    public void setDataPlace(String dataPlace) {
        this.dataPlace = dataPlace;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDataRoom() {
        return dataRoom;
    }

    public void setDataRoom(String dataRoom) {
        this.dataRoom = dataRoom;
    }

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }

    public Date getDataTimestamp() {
        return dataTimestamp;
    }

    public void setDataTimestamp(Date dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
