package com.michal.mqtt.api.auth.model.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "User login data")
public class LoginUserModel implements Serializable{

    @NotEmpty
    @ApiModelProperty(value = "login", allowableValues = "login", required = true)
    private String userName;

    @NotEmpty
    @ApiModelProperty(value = "password", allowableValues = "password", required = true)
    private String password;

    public LoginUserModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
