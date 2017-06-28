package com.michal.config;

import com.michal.mqtt.api.converter.request.BrokerModelToBrokerConverter;
import com.michal.mqtt.api.converter.response.MqttClientToClientModelConverter;
import com.michal.mqtt.api.converter.response.NotificationToNotificationModelConverter;
import com.michal.mqtt.api.converter.response.SensorDataModelConverter;
import com.michal.mqtt.api.converter.response.TopicToTopicModelConverter;
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

    @Bean
    public NotificationToNotificationModelConverter notificationToNotificationModelConverter(){
        return new NotificationToNotificationModelConverter();
    }

    @Bean
    public BrokerModelToBrokerConverter brokerModelToBrokerConverter(){
        return new BrokerModelToBrokerConverter();
    }
}
