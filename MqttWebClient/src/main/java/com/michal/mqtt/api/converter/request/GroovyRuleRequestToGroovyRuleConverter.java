package com.michal.mqtt.api.converter.request;

import com.michal.dao.api.GroovyRuleDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.groovyrule.model.request.GroovyRulRequest;

public class GroovyRuleRequestToGroovyRuleConverter extends Converter<GroovyRulRequest, GroovyRule> {

    private SensorDao sensorDao;

    public GroovyRuleRequestToGroovyRuleConverter(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @Override
    public GroovyRule convert(GroovyRulRequest groovyRulRequest) {
        if(groovyRulRequest!=null){
            GroovyRule groovyRule = new GroovyRule();
            groovyRule.setType(groovyRulRequest.getType());
            groovyRule.setSensor(sensorDao.get(new Long(groovyRulRequest.getSensorId())));
            groovyRule.setActions(groovyRulRequest.getActions());
            groovyRule.setDescription(groovyRulRequest.getDescription());
            groovyRule.setMessage(groovyRulRequest.getMessage());
            groovyRule.setRule(groovyRulRequest.getRule());
            return groovyRule;
        }
        return null;
    }
}
