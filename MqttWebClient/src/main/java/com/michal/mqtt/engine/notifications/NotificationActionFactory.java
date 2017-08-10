package com.michal.mqtt.engine.notifications;

import com.michal.dao.model.rule.Action;

public class NotificationActionFactory {

    private EmailAction emailAction;

    public NotificationActionFactory(EmailAction emailAction) {
        this.emailAction = emailAction;
    }

    public NotificationAction getAction(Action.ActionType actionType){
        if(actionType.equals(Action.ActionType.MAIL)){
            return emailAction;
        }else{
            return null;
        }
    }

}
