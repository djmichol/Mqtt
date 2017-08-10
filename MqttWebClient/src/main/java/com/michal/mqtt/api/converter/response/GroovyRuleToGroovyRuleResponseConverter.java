package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.groovyrule.model.response.GroovyRuleResponse;

import java.util.stream.Collectors;

public class GroovyRuleToGroovyRuleResponseConverter extends Converter<GroovyRule, GroovyRuleResponse>{

    private ActionToActionResponseConverter actionToActionResponseConverter;

    public GroovyRuleToGroovyRuleResponseConverter(ActionToActionResponseConverter actionToActionResponseConverter) {
        this.actionToActionResponseConverter = actionToActionResponseConverter;
    }

    @Override
    public GroovyRuleResponse convert(GroovyRule groovyRule) {
        if(groovyRule!=null){
            GroovyRuleResponse groovyRuleResponse = new GroovyRuleResponse();
            groovyRuleResponse.setActions(actionToActionResponseConverter.convert(groovyRule.getActions().stream().collect(Collectors.toList())));
            groovyRuleResponse.setDescription(groovyRule.getDescription());
            groovyRuleResponse.setId(groovyRule.getId());
            groovyRuleResponse.setRule(groovyRule.getRule());
            groovyRuleResponse.setSensorId(groovyRule.getSensor().getId());
            groovyRuleResponse.setSensorName(groovyRule.getSensor().getName());
            groovyRuleResponse.setType(groovyRule.getType());
            return groovyRuleResponse;
        }
        return null;
    }
}
