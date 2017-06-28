package com.michal.mqtt.api.model.request;

import com.michal.mqtt.callback.topic.CallbackEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "Mqtt broker data")
public class BrokerRequestModel implements Serializable {

    public BrokerRequestModel() {
    }

    @NotNull
    @ApiModelProperty(value = "url", allowableValues = "tcp://localhost:1883", required = true)
    private String url;
    @NotNull
    @ApiModelProperty(value = "user", allowableValues = "user", required = true)
    private String user;
    @NotNull
    @ApiModelProperty(value = "password", allowableValues = "password", required = true)
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
