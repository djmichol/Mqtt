package com.michal.mqtt.api.groovyrule.model.response;

import com.michal.dao.model.rule.GroovyRule;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Groovy rule response model")
public class GroovyRuleResponse implements Serializable{

    private Long id;
    private String rule;
    private String description;
    private String message;
    private GroovyRule.VariableType type;
    private List<ActionResponse> actions;
    private String sensorName;
    private Long sensorId;

    public GroovyRuleResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GroovyRule.VariableType getType() {
        return type;
    }

    public void setType(GroovyRule.VariableType type) {
        this.type = type;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public List<ActionResponse> getActions() {
        return actions;
    }

    public void setActions(List<ActionResponse> actions) {
        this.actions = actions;
    }
}
