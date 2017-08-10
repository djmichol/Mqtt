package com.michal.mqtt.api.mqtt.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "Notification response model")
public class ReceivedMessagesResponseModel implements Serializable {

    public ReceivedMessagesResponseModel() {
    }

    @ApiModelProperty(value = "message")
    private String message;
    @ApiModelProperty(value = "topic")
    private String topic;
    @ApiModelProperty(value = "timeStamp")
    private Date dataTimestamp;
    @ApiModelProperty(value = "brokerId")
    private Long brokerId;
    @ApiModelProperty(value = "brokerName")
    private String brokerName;

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

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }
}
