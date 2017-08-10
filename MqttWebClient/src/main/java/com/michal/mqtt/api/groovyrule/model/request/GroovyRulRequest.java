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
    @NotNull(message = "Rule can't be empty!")
    @ApiModelProperty(value = "rule",required = true)
    private GroovyRule.VariableType type;
    @ApiModelProperty(value = "rule", required = false)
    private List<String> actionsIds;
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

    public GroovyRule.VariableType getType() {
        return type;
    }

    public void setType(GroovyRule.VariableType type) {
        this.type = type;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public List<String> getActionsIds() {
        return actionsIds;
    }

    public void setActionsIds(List<String> actionsIds) {
        this.actionsIds = actionsIds;
    }
}
