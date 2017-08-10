package com.michal.config;

import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.api.SensorDataDao;
import com.michal.mqtt.engine.notifications.EmailAction;
import com.michal.mqtt.engine.notifications.NotificationActionFactory;
import com.michal.mqtt.engine.rulesengine.GroovyRuleEngine;
import com.michal.mqtt.engine.client.ReceivedMessageExtractor;
import com.michal.mqtt.engine.client.SensorDataListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataListenersConfiguration {
    @Bean
    public SensorDataListener sensorDataListener(SensorDataDao sensorDataDao) {
        return new SensorDataListener(sensorDataDao);
    }

    @Bean
    public ReceivedMessageExtractor recivedMessageExtractor(SensorDao sensorDao, SensorDataListener sensorDataListener, DictionaryDefinitionDao dictionaryDefinitionDao,
                                                            GroovyRuleEngine groovyRuleEngine, NotificationActionFactory notificationActionFactory) {
        return new ReceivedMessageExtractor(sensorDao, sensorDataListener, dictionaryDefinitionDao, groovyRuleEngine, notificationActionFactory);
    }

    @Bean
    public NotificationActionFactory notificationActionFactory(EmailAction mailAction) {
        return new NotificationActionFactory(mailAction);
    }

    @Bean
    public GroovyRuleEngine groovyRuleEngine() {
        return new GroovyRuleEngine();
    }

    @Bean
    public EmailAction emailAction(Environment env) {
        return new EmailAction(env);
    }
}
