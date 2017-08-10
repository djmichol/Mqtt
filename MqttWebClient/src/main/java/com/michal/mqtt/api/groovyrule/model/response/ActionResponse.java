package com.michal.mqtt.api.groovyrule.model.response;

import com.michal.dao.model.rule.Action;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "Rule action response model")
public class ActionResponse implements Serializable {

    private Long id;
    private Action.ActionType type;
    private String addressee;
    private String message;
    private String subject;

    public ActionResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action.ActionType getType() {
        return type;
    }

    public void setType(Action.ActionType type) {
        this.type = type;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
