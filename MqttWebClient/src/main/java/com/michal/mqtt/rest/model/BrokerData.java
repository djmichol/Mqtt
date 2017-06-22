package com.michal.mqtt.rest.model;

import com.michal.mqtt.callback.CallbackEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BrokerData implements Serializable {

    public BrokerData() {
    }

    @NotNull
    private String url;
    @NotNull
    private String user;
    @NotNull
    private String password;
    @NotNull
    private CallbackEnum callbackEnum;

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

    public CallbackEnum getCallbackEnum() {
        return callbackEnum;
    }

    public void setCallbackEnum(CallbackEnum callbackEnum) {
        this.callbackEnum = callbackEnum;
    }
}
