package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.groovyrule.model.response.GroovyRuleResponse;

public class GroovyRuleToGroovyRuleResponseConverter extends Converter<GroovyRule, GroovyRuleResponse>{
    @Override
    public GroovyRuleResponse convert(GroovyRule groovyRule) {
        if(groovyRule!=null){
            GroovyRuleResponse groovyRuleResponse = new GroovyRuleResponse();
            groovyRuleResponse.setActions(groovyRule.getActions());
            groovyRuleResponse.setDescription(groovyRule.getDescription());
            groovyRuleResponse.setId(groovyRule.getId());
            groovyRuleResponse.setMessage(groovyRule.getMessage());
            groovyRuleResponse.setRule(groovyRule.getRule());
            groovyRuleResponse.setSensorId(groovyRule.getSensor().getId());
            groovyRuleResponse.setSensorName(groovyRule.getSensor().getName());
            groovyRuleResponse.setType(groovyRule.getType());
            return groovyRuleResponse;
        }
        return null;
    }
}
