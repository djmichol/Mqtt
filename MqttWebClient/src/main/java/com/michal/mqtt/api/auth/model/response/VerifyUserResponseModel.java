package com.michal.mqtt.api.auth.model.response;

import java.io.Serializable;

public class VerifyUserResponseModel implements Serializable{

    private boolean authenticationVerified;

    private boolean tokenExpired;

    private VerifyUserResponseModel(boolean authenticationVerified, boolean tokenExpired) {
        this.authenticationVerified = authenticationVerified;
        this.tokenExpired = tokenExpired;
    }

    public static VerifyUserResponseModel verified(){
        return new VerifyUserResponseModel(true, false);
    }

    public static VerifyUserResponseModel notVerified(){
        return new VerifyUserResponseModel(false, false);
    }

    public static VerifyUserResponseModel tokenExppired(){
        return new VerifyUserResponseModel(false, false);
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(boolean tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public boolean isAuthenticationVerified() {
        return authenticationVerified;
    }

    public void setAuthenticationVerified(boolean authenticationVerified) {
        this.authenticationVerified = authenticationVerified;
    }
}
