package com.michal.mqtt.api.model.request;

import com.michal.mqtt.callback.topic.CallbackEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Topic with callback")
public class TopicRequestModelWithCallback extends TopicRequestModel{

    @NotNull(message = "Topic callback can't be empty!")
    @ApiModelProperty(value = "topicCallback", required = true)
    private CallbackEnum topicCallback;

    public CallbackEnum getTopicCallback() {
        return topicCallback;
    }

    public void setTopicCallback(CallbackEnum topicCallback) {
        this.topicCallback = topicCallback;
    }
}
