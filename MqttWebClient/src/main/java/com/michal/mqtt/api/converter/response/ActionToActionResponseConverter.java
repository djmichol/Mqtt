package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.rule.Action;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.groovyrule.ActionApi;
import com.michal.mqtt.api.groovyrule.model.response.ActionResponse;
import com.michal.mqtt.api.networkstructure.ClientsApi;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ActionToActionResponseConverter extends ResponseConverter<Action, ActionResponse> {
    @Override
    public ActionResponse convert(Action action) {
        if(action!=null){
            ActionResponse actionResponse = new ActionResponse();
            actionResponse.setAddressee(action.getAddressee());
            actionResponse.setMessage(action.getMessage());
            actionResponse.setSubject(action.getSubject());
            actionResponse.setType(action.getType());
            prepareLinks(action, actionResponse);
            return actionResponse;
        }
        return null;
    }

    @Override
    protected void prepareLinks(Action action, ActionResponse actionResponse) {
        Link detail = linkTo(methodOn(ActionApi.class).getActionDetails(action.getId())).withSelfRel();
        actionResponse.add(detail);
    }
}
