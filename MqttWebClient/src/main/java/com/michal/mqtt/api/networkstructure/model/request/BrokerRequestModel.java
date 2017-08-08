package com.michal.mqtt.api.networkstructure.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "Mqtt broker data")
public class BrokerRequestModel implements Serializable {

    public BrokerRequestModel() {
    }

    @NotEmpty(message = "URL can't be empty!")
    @ApiModelProperty(value = "url", allowableValues = "tcp://localhost:1883", required = true)
    private String url;
    @NotEmpty(message = "User can't be empty!")
    @ApiModelProperty(value = "user", allowableValues = "user", required = true)
    private String user;
    @NotEmpty(message = "Password can't be empty!")
    @ApiModelProperty(value = "password", allowableValues = "password", required = true)
    private String password;
    @NotEmpty(message = "Name can't be empty!")
    @ApiModelProperty(value = "name", allowableValues = "name", required = true)
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
