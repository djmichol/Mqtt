package com.michal.mqtt.api.converter.request;

import com.michal.dao.model.rule.Action;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.groovyrule.model.request.ActionRequest;

public class ActionRequestToActionConverter extends Converter<ActionRequest, Action>{
    @Override
    public Action convert(ActionRequest actionRequest) {
        if(actionRequest!=null){
            Action action = new Action();
            action.setAddressee(actionRequest.getAddressee());
            action.setMessage(actionRequest.getMessage());
            action.setSubject(actionRequest.getSubject());
            action.setType(actionRequest.getType());
            return action;
        }
        return null;
    }
}
