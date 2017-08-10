package com.michal.mqtt.api.groovyrule.model.request;

import com.michal.dao.model.rule.GroovyRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Groovy rule data")
public class GroovyRulRequest implements Serializable {

    @NotEmpty(message = "Rule can't be empty!")
    @ApiModelProperty(value = "rule", allowableValues = "1 == 1", required = true)
    private String rule;
    @ApiModelProperty(value = "rule", allowableValues = "description", required = false)
    private String description;
    @NotEmpty(message = "message can't be empty!")
    @ApiModelProperty(value = "message", allowableValues = "message", required = true)
    private String message;
    @NotNull(message = "Rule can't be empty!")
    @ApiModelProperty(value = "rule",required = true)
    private GroovyRule.VariableType type;
    @ApiModelProperty(value = "rule", required = true)
    private List<GroovyRule.ActionType> actions;
    @NotEmpty(message = "sensorId can't be empty!")
    @ApiModelProperty(value = "sensorId", allowableValues = "1", required = true)
    private String sensorId;

    public GroovyRulRequest() {
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

    public List<GroovyRule.ActionType> getActions() {
        return actions;
    }

    public void setActions(List<GroovyRule.ActionType> actions) {
        this.actions = actions;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
}
