package com.michal.mqtt.api.auth.model.response;

import java.io.Serializable;

public class SignResponseModel extends LoginResponseModel implements Serializable{

    public SignResponseModel() {
    }

    private boolean userCreated;

    public SignResponseModel(String token, VerifyPasswordModel verifyPasswordModel, boolean userCreated) {
        super(token, verifyPasswordModel);
        this.userCreated = userCreated;
    }

    public boolean isUserCreated() {
        return userCreated;
    }

    public void setUserCreated(boolean userCreated) {
        this.userCreated = userCreated;
    }
}
