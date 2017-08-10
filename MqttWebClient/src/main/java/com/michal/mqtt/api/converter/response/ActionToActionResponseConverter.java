package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.rule.Action;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.groovyrule.model.response.ActionResponse;

public class ActionToActionResponseConverter extends Converter<Action, ActionResponse>{
    @Override
    public ActionResponse convert(Action action) {
        if(action!=null){
            ActionResponse actionResponse = new ActionResponse();
            actionResponse.setAddressee(action.getAddressee());
            actionResponse.setId(action.getId());
            actionResponse.setMessage(action.getMessage());
            actionResponse.setSubject(action.getSubject());
            actionResponse.setType(action.getType());
            return actionResponse;
        }
        return null;
    }
}
