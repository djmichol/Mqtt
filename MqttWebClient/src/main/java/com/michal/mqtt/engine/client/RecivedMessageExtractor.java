package com.michal.mqtt.engine.client;

import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.model.dictionary.DictionaryDefinition;
import com.michal.dao.model.dictionary.DictionaryValues;
import com.michal.dao.model.networkstructure.Sensor;
import com.michal.dao.model.rule.GroovyRule;
import com.michal.mqtt.engine.notifications.NotificationActionFactory;
import com.michal.mqtt.engine.rulesengine.GroovyRuleEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RecivedMessageExtractor {

    private SensorDao sensorDao;
    private SensorDataListener sensorDataListener;
    private DictionaryDefinitionDao dictionaryDefinitionDao;
    private GroovyRuleEngine groovyRuleEngine;
    private NotificationActionFactory notificationActionFactory;

    public RecivedMessageExtractor(SensorDao sensorDao, SensorDataListener sensorDataListener, DictionaryDefinitionDao dictionaryDefinitionDao, GroovyRuleEngine
            groovyRuleEngine, NotificationActionFactory notificationActionFactory) {
        this.sensorDao = sensorDao;
        this.sensorDataListener = sensorDataListener;
        this.dictionaryDefinitionDao = dictionaryDefinitionDao;
        this.groovyRuleEngine = groovyRuleEngine;
        this.notificationActionFactory = notificationActionFactory;
    }

    private String topic;
    private String message;
    private Sensor sensor;
    private Map<GroovyRule.ActionType, String> trueRules;

    public RecivedMessageExtractor message(String message) {
        this.message = message;
        return this;
    }

    public RecivedMessageExtractor topic(String topic) {
        this.topic = topic;
        return this;
    }

    public RecivedMessageExtractor extractSensor() {
        Sensor sensor = sensorDao.getByTopic(topic);
        this.sensor = sensor;
        return this;
    }

    public RecivedMessageExtractor checkRules() {
        trueRules = new HashMap<>();
        this.sensor.getRules().forEach(groovyRule -> {
            if (groovyRuleEngine.evaluate(groovyRule.getRule(), groovyRuleEngine.getVariables(groovyRule, this.message))) {
                groovyRule.getActions().forEach(actionType -> trueRules.put(actionType, groovyRule.getMessage()));
            }
        });
        return this;
    }

    public RecivedMessageExtractor executeNotificationActions() {
        trueRules.entrySet().forEach(actionTypeStringEntry -> notificationActionFactory.getAction(actionTypeStringEntry.getKey()).sendNotification(actionTypeStringEntry.getValue
                ()));
        return this;
    }

    private SensorMessageListener checkType() {
        DictionaryDefinition dictionaryDefinition = dictionaryDefinitionDao.getDictionaryDefinitionByCode("SENSOR_TYPES");
        if (dictionaryDefinition.getValues().stream().map(DictionaryValues::getDictValVal).collect(Collectors.toList()).contains(sensor.getType())) {
            return sensorDataListener;
        }
        return sensorDataListener;
    }

    public void saveMessage() {
        SensorMessageListener sensorMessageListener = checkType();
        sensorMessageListener.mesageRecived(this.sensor, this.topic, this.message);
    }

}
