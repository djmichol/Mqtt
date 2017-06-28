package com.michal.config;

import com.michal.mqtt.api.converter.MqttClientToClientModelConverter;
import com.michal.mqtt.api.converter.SensorDataModelConverter;
import com.michal.mqtt.api.converter.TopicToTopicModelConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public SensorDataModelConverter sensorDataModelConverter(){
        return new SensorDataModelConverter();
    }

    @Bean
    public MqttClientToClientModelConverter mqttClientToClientModelConverter(TopicToTopicModelConverter topicToTopicModelConverter) {
        return new MqttClientToClientModelConverter(topicToTopicModelConverter);
    }

    @Bean
    public TopicToTopicModelConverter topicToTopicModelConverter(){
        return new TopicToTopicModelConverter();
    }
}
