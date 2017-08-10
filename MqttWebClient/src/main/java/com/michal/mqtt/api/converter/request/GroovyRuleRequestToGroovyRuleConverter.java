package com.michal.mqtt.api.converter.request;

import com.michal.dao.api.ActionDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.model.rule.Action;
import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.groovyrule.model.request.GroovyRulRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroovyRuleRequestToGroovyRuleConverter extends Converter<GroovyRulRequest, GroovyRule> {

    private SensorDao sensorDao;
    private ActionDao actionDao;

    public GroovyRuleRequestToGroovyRuleConverter(SensorDao sensorDao, ActionDao actionDao) {
        this.sensorDao = sensorDao;
        this.actionDao = actionDao;
    }

    @Override
    public GroovyRule convert(GroovyRulRequest groovyRulRequest) {
        if(groovyRulRequest!=null){
            GroovyRule groovyRule = new GroovyRule();
            groovyRule.setType(groovyRulRequest.getType());
            groovyRule.setSensor(sensorDao.get(new Long(groovyRulRequest.getSensorId())));
            groovyRule.setActions(getActions(groovyRulRequest.getActionsIds()));
            groovyRule.setDescription(groovyRulRequest.getDescription());
            groovyRule.setRule(groovyRulRequest.getRule());
            return groovyRule;
        }
        return null;
    }

    private Set<Action> getActions(List<String> actoinIds){
        Set<Action> actions = new HashSet<>();
        actoinIds.forEach(actionId -> actions.add(actionDao.get(new Long(actionId))));
        return actions;
    }
}
