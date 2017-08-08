package com.michal.mqtt.api.auth.model.response;

import java.io.Serializable;

public class LoginResponseModel implements Serializable{

    private String token;

    private VerifyPasswordModel verifyPasswordModel;

    public LoginResponseModel() {
    }

    public LoginResponseModel(String token, VerifyPasswordModel verifyPasswordModel) {
        this.token = token;
        this.verifyPasswordModel = verifyPasswordModel;
    }

    public LoginResponseModel(VerifyPasswordModel verifyPasswordModel) {
        this.verifyPasswordModel = verifyPasswordModel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VerifyPasswordModel getVerifyPasswordModel() {
        return verifyPasswordModel;
    }

    public void setVerifyPasswordModel(VerifyPasswordModel verifyPasswordModel) {
        this.verifyPasswordModel = verifyPasswordModel;
    }
}
