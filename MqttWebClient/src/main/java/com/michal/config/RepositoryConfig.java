package com.michal.config;

import com.michal.dao.BrokerDao;
import com.michal.dao.SensorDataDao;
import com.michal.dao.TopicDao;
import com.michal.dao.impl.BrokerDaoImpl;
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

}
