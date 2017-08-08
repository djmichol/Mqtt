package com.michal.mqtt.api.mqtt.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Sensor data")
public class SensorDataResponseModel implements Serializable {

    public SensorDataResponseModel() {
    }

    @ApiModelProperty(value = "type")
    private String dataType;
    @ApiModelProperty(value = "data")
    private String sensorData;
    @ApiModelProperty(value = "timeStamp")
    private Date dataTimestamp;
    @ApiModelProperty(value = "sensorId")
    private Long sensorId;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }
}
