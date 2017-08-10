package com.michal.mqtt.engine.notifications;

import com.michal.dao.model.rule.GroovyRule;

public class NotificationActionFactory {

    private EmailAction emailAction;

    public NotificationActionFactory(EmailAction emailAction) {
        this.emailAction = emailAction;
    }

    public NotificationAction getAction(GroovyRule.ActionType actionType){
        if(actionType.equals(GroovyRule.ActionType.MAIL)){
            return emailAction;
        }else{
            return null;
        }
    }

}
