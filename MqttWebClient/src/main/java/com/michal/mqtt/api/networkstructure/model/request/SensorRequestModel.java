package com.michal.mqtt.api.networkstructure.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "SensorRequestModel to add new sensor")
public class SensorRequestModel {

    @ApiModelProperty(value = "name")
    @NotEmpty(message = "name can't be empty!")
    private String name;
    @ApiModelProperty(value = "nodeId")
    @NotNull(message = "nodeId can't be empty!")
    private Long nodeId;
    @ApiModelProperty(value = "type")
    @NotEmpty(message = "type can't be empty!")
    private String type;
    @ApiModelProperty(value = "type")
    private String topic;

    public SensorRequestModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
