package com.michal.mqtt.api.model.response;

import com.michal.mqtt.callback.CallbackEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Client response model")
public class ClientResponseModel implements Serializable{

    public ClientResponseModel() {
    }

    @ApiModelProperty(value = "brokerId")
    private Long brokerId;

    @ApiModelProperty(value = "brokerUrl")
    private String brokerUrl;

    @ApiModelProperty(value = "brokerUser")
    private String brokerUser;

    @ApiModelProperty(value = "brokerCallback")
    private CallbackEnum brokerCallback;

    @ApiModelProperty(value = "topics")
    private List<TopicResponseModel> topics;

    @ApiModelProperty(value = "connected")
    private Boolean connected;

    public Long getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public String getBrokerUser() {
        return brokerUser;
    }

    public void setBrokerUser(String brokerUser) {
        this.brokerUser = brokerUser;
    }

    public CallbackEnum getBrokerCallback() {
        return brokerCallback;
    }

    public void setBrokerCallback(CallbackEnum brokerCallback) {
        this.brokerCallback = brokerCallback;
    }

    public List<TopicResponseModel> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicResponseModel> topics) {
        this.topics = topics;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }
}
