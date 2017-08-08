package com.michal.mqtt.api.auth.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "New user data")
public class CreateUserModel implements Serializable {

    @NotEmpty
    @ApiModelProperty(value = "login", allowableValues = "login", required = true)
    private String login;
    @NotEmpty
    @ApiModelProperty(value = "password", allowableValues = "password", required = true)
    private String password;
    @NotEmpty
    @Email
    @ApiModelProperty(value = "email", allowableValues = "emial@email.com", required = true)
    private String email;

    public CreateUserModel() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
