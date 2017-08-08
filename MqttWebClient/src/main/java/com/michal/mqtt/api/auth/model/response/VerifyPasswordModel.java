package com.michal.mqtt.api.auth.model.response;

import java.io.Serializable;

public class VerifyPasswordModel implements Serializable {

    private boolean authenticated;

    private boolean canChangePassword;

    private boolean userLocked;

    private VerifyPasswordModel(boolean authenticated, boolean canChangePassword, boolean userLocked) {
        this.authenticated = authenticated;
        this.canChangePassword = canChangePassword;
        this.userLocked = userLocked;
    }

    public static VerifyPasswordModel unauthorized(){
        return new VerifyPasswordModel(false,false,false);
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean isCanChangePassword() {
        return canChangePassword;
    }

    public boolean isUserLocked() { return userLocked; }

    public void setAuthenticated(boolean authenticated) { this.authenticated = authenticated; }

    public void setCanChangePassword(boolean canChangePassword) { this.canChangePassword = canChangePassword; }

    public void setUserLocked(boolean userLocked) { this.userLocked = userLocked; }

    public VerifyPasswordModel() {
    }
}
