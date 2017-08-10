package com.michal.mqtt.api.groovyrule.model.request;

import com.michal.dao.model.rule.Action;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "Action data")
public class ActionRequest implements Serializable{

    @NotNull(message = "type can't be empty!")
    @ApiModelProperty(value = "type", required = true)
    private Action.ActionType type;
    @NotEmpty(message = "addressee can't be empty!")
    @ApiModelProperty(value = "addressee", required = true)
    private String addressee;
    @NotEmpty(message = "message can't be empty!")
    @ApiModelProperty(value = "message", required = true)
    private String message;
    @ApiModelProperty(value = "subject", required = false)
    private String subject;

    public ActionRequest() {
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

