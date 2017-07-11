package com.michal.mqtt.api.model.request;

import com.michal.mqtt.callback.topic.CallbackEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "Topic in broker")
public class TopicRequestModel implements Serializable {

    public TopicRequestModel() {
    }

    @NotNull
    @ApiModelProperty(value = "broker id", required = true)
    private Long brokerId;
    @NotEmpty(message = "Topic can't be empty!")
    @ApiModelProperty(value = "topic", allowableValues = "home/kitchen/temperature", required = true)
    private String topic;

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
}
