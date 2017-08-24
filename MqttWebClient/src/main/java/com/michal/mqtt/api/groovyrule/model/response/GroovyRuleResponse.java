package com.michal.mqtt.api.groovyrule.model.response;

import com.michal.dao.model.rule.GroovyRule;
import io.swagger.annotations.ApiModel;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Groovy rule response model")
public class GroovyRuleResponse extends ResourceSupport {

    private String rule;
    private String description;
    private String message;
    private GroovyRule.VariableType type;

    public GroovyRuleResponse() {
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
}
