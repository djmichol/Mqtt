package com.michal.mqtt.api.mqtt.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Sensor data")
public class SensorDataResponseModel extends ResourceSupport implements Serializable {

    public SensorDataResponseModel() {
    }

    @ApiModelProperty(value = "type")
    private String dataType;
    @ApiModelProperty(value = "data")
    private String sensorData;
    @ApiModelProperty(value = "timeStamp")
    private Date dataTimestamp;

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
}
