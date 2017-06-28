package com.michal.mqtt.api.model.request;

import com.michal.mqtt.callback.topic.CallbackEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Topic with callback")
public class TopicRequestModelWithCallback extends TopicRequestModel{

    @ApiModelProperty(value = "topicCallback", required = true)
    private CallbackEnum topicCallback;

    public CallbackEnum getTopicCallback() {
        return topicCallback;
    }

    public void setTopicCallback(CallbackEnum topicCallback) {
        this.topicCallback = topicCallback;
    }
}
