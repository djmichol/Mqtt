package com.michal.config;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.DictionaryValuesDao;
import com.michal.dao.api.NodeDao;
import com.michal.dao.api.RecivedMessageDao;
import com.michal.dao.api.RoomDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.api.SensorDataDao;
import com.michal.dao.impl.BrokerDaoImpl;
import com.michal.dao.impl.DictionaryDefinitionDaoImpl;
import com.michal.dao.impl.DictionaryValueDaoImpl;
import com.michal.dao.impl.NodeDaoImpl;
import com.michal.dao.impl.RecivedMessageDaoImpl;
import com.michal.dao.impl.RoomDaoImpl;
import com.michal.dao.impl.SendMessageDaoImpl;
import com.michal.dao.impl.SensorDaoImpl;
import com.michal.dao.impl.SensorDataDaoImpl;
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
    public RoomDao placesDao() {
        return new RoomDaoImpl();
    }

    @Bean
    public DictionaryDefinitionDao dictionaryDefinitionDao() {
        return new DictionaryDefinitionDaoImpl();
    }

    @Bean
    public DictionaryValuesDao dictionaryValuesDao() {
        return new DictionaryValueDaoImpl();
    }

    @Bean
    public NodeDao brokerNodeDao() {
        return new NodeDaoImpl();
    }

    @Bean
    public SensorDao nodeSensorDao() {
        return new SensorDaoImpl();
    }

    @Bean
    public SendMessageDao sendMessageDao(){
        return new SendMessageDaoImpl();
    }

    @Bean
    public RecivedMessageDao recivedMessageDao(){
        return new RecivedMessageDaoImpl();
    }

}
