package com.michal.mqtt.api.model.response;

import com.michal.mqtt.callback.topic.CallbackEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Topic response model")
public class TopicResponseModel {
    public TopicResponseModel(){}

    @ApiModelProperty(value = "broker id")
    private Long brokerId;
    @ApiModelProperty(value = "topic")
    private String topic;
    @ApiModelProperty(value = "subscribed")
    private boolean subscribed;
    @ApiModelProperty(value = "topicCallback")
    private CallbackEnum topicCallback;


    public Long getBrokerId() {
        return brokerId;
    }
    public void setBrokerId(Long brokerId) {
        this.brokerId = brokerId;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

    public CallbackEnum getTopicCallback() {
        return topicCallback;
    }

    public void setTopicCallback(CallbackEnum topicCallback) {
        this.topicCallback = topicCallback;
    }
}
