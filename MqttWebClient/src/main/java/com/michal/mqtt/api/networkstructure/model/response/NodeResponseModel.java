package com.michal.mqtt.api.networkstructure.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(description = "Node response model")
public class NodeResponseModel {
    public NodeResponseModel(){}

    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "name")
    private String name;
    @ApiModelProperty(value = "broker id")
    private Long brokerId;
    @ApiModelProperty(value = "broker_name")
    private String brokerName;
    @ApiModelProperty(value = "url")
    private String url;
    @ApiModelProperty(value = "status")
    private String status;
    @ApiModelProperty(value = "last seen")
    private Date lastSeen;
    @ApiModelProperty(value = "status")
    private List<SensorResponseModel> sensors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public List<SensorResponseModel> getSensors() {
        return sensors;
    }

    public void setSensors(List<SensorResponseModel> sensors) {
        this.sensors = sensors;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }
}
