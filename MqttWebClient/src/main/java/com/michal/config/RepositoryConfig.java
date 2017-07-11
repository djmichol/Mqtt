package com.michal.config;

import com.michal.dao.BrokerDao;
import com.michal.dao.DictionaryDefinitionDao;
import com.michal.dao.DictionaryValuesDao;
import com.michal.dao.NotificationDao;
import com.michal.dao.PlacesDao;
import com.michal.dao.SensorDataDao;
import com.michal.dao.TopicDao;
import com.michal.dao.impl.BrokerDaoImpl;
import com.michal.dao.impl.DictionaryDefinitionDaoImpl;
import com.michal.dao.impl.DictionaryValueDaoImpl;
import com.michal.dao.impl.NotificationDaoImpl;
import com.michal.dao.impl.PlacesDaoImpl;
import com.michal.dao.impl.SensorDataDaoImpl;
import com.michal.dao.impl.TopicDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public BrokerDao brokerDao() {
        return new BrokerDaoImpl();
    }

    @Bean
    public SensorDataDao sensorDataDao() {
        return new SensorDataDaoImpl();
    }

    @Bean
    public TopicDao topicDao() {
        return new TopicDaoImpl();
    }

    @Bean
    public NotificationDao notificationDao() {
        return new NotificationDaoImpl();
    }

    @Bean
    public PlacesDao placesDao(){
        return new PlacesDaoImpl();
    }

    @Bean
    public DictionaryDefinitionDao dictionaryDefinitionDao(){
        return new DictionaryDefinitionDaoImpl();
    }

    @Bean
    public DictionaryValuesDao dictionaryValuesDao(){
        return new DictionaryValueDaoImpl();
    }

}
