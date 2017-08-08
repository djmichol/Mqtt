package com.michal.mqtt.api.auth.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "User auth data")
public class VerifyUserModel implements Serializable{

    @NotEmpty
    @ApiModelProperty(value = "generated user token", allowableValues = "token", required = true)
    private String token;

    @NotEmpty
    @ApiModelProperty(value = "login", allowableValues = "login", required = true)
    private String userLogin;

    public VerifyUserModel() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
