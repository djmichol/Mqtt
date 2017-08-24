package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.groovyrule.ActionApi;
import com.michal.mqtt.api.groovyrule.GroovyRuleApi;
import com.michal.mqtt.api.groovyrule.model.response.GroovyRuleResponse;
import com.michal.mqtt.api.networkstructure.SensorsApi;
import org.springframework.hateoas.Link;

import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GroovyRuleToGroovyRuleResponseConverter extends ResponseConverter<GroovyRule, GroovyRuleResponse> {

    private ActionToActionResponseConverter actionToActionResponseConverter;

    public GroovyRuleToGroovyRuleResponseConverter(ActionToActionResponseConverter actionToActionResponseConverter) {
        this.actionToActionResponseConverter = actionToActionResponseConverter;
    }

    @Override
    public GroovyRuleResponse convert(GroovyRule groovyRule) {
        if(groovyRule!=null){
            GroovyRuleResponse groovyRuleResponse = new GroovyRuleResponse();
            groovyRuleResponse.setDescription(groovyRule.getDescription());
            groovyRuleResponse.setRule(groovyRule.getRule());
            groovyRuleResponse.setType(groovyRule.getType());
            prepareLinks(groovyRule, groovyRuleResponse);
            return groovyRuleResponse;
        }
        return null;
    }

    @Override
    protected void prepareLinks(GroovyRule groovyRule, GroovyRuleResponse groovyRuleResponse) {
        Link actions = linkTo(methodOn(ActionApi.class).getRuleActions(groovyRule.getId())).withRel("groovyRule.actions");
        groovyRuleResponse.add(actions);
        if(groovyRule.getSensor()!=null) {
            Link sensor = linkTo(methodOn(SensorsApi.class).getSensorDetails(groovyRule.getSensor().getId())).withRel("groovyRule.sensor");
            groovyRuleResponse.add(sensor);
        }
        Link detail = linkTo(methodOn(GroovyRuleApi.class).getRuleDetails(groovyRule.getId())).withSelfRel();
        groovyRuleResponse.add(detail);

    }
}
