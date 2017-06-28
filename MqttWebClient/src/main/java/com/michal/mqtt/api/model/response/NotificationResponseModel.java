package com.michal.mqtt.api.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Notification response model")
public class NotificationResponseModel implements Serializable{

    public NotificationResponseModel() {
    }

    @ApiModelProperty(value = "message")
    private String message;
    @ApiModelProperty(value = "topic")
    private String topic;
    @ApiModelProperty(value = "timeStamp")
    private Date dataTimestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDataTimestamp() {
        return dataTimestamp;
    }

    public void setDataTimestamp(Date dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }
}
