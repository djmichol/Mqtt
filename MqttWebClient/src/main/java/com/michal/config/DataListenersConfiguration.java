package com.michal.config;

import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.api.SensorDataDao;
import com.michal.mqtt.callback.client.RecivedMessageExtractor;
import com.michal.mqtt.callback.client.SensorDataListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataListenersConfiguration {
    @Bean
    public SensorDataListener sensorDataListener(SensorDataDao sensorDataDao) {
        return new SensorDataListener(sensorDataDao);
    }

    @Bean
    public RecivedMessageExtractor recivedMessageExtractor(SensorDao sensorDao, SensorDataListener sensorDataListener, DictionaryDefinitionDao dictionaryDefinitionDao) {
        return new RecivedMessageExtractor(sensorDao, sensorDataListener, dictionaryDefinitionDao);
    }
}
