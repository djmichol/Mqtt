package com.michal.config;

import com.michal.dao.BrokerDao;
import com.michal.dao.SensorDataDao;
import com.michal.dao.TopicDao;
import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.rest.MessageApi;
import com.michal.mqtt.rest.ClientsApi;
import com.michal.mqtt.rest.SensorDataApi;
import com.michal.mqtt.rest.TopicsApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApiConfigurtaion {

    @Bean
    @Scope("singleton")
    public MqttApplication application(BrokerDao brokerRepo){
        return new MqttApplication(brokerRepo);
    }

    @Bean
    public MessageApi messageApi(MqttApplication mqttApplication) {
        return new MessageApi(mqttApplication);
    }

    @Bean
    public SensorDataApi sensorDataApi(SensorDataDao sensorDataRepo) {
        return new SensorDataApi(sensorDataRepo);
    }

    @Bean
    public TopicsApi topicsApi(TopicDao topicRepo, MqttApplication mqttApplication) {
        return new TopicsApi(topicRepo, mqttApplication);
    }

    @Bean
    public ClientsApi mqttClientsApi(MqttApplication mqttApplication, BrokerDao brokerRepo) {
        return new ClientsApi(mqttApplication, brokerRepo);
    }

}
