package com.michal.config;

import com.michal.dao.DictionaryDefinitionDao;
import com.michal.mqtt.api.converter.request.BrokerModelToBrokerConverter;
import com.michal.mqtt.api.converter.request.DictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
import com.michal.mqtt.api.converter.request.DictionaryValueModelToDictionaryValuesConverter;
import com.michal.mqtt.api.converter.request.PlaceRequestModelToPlaceConverter;
import com.michal.mqtt.api.converter.response.DictionaryToDictionaryResponseModelConverter;
import com.michal.mqtt.api.converter.response.MqttClientToClientModelConverter;
import com.michal.mqtt.api.converter.response.NotificationToNotificationModelConverter;
import com.michal.mqtt.api.converter.response.PlaceToPlaceResponseModelConverter;
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

    @Bean
    public PlaceToPlaceResponseModelConverter placeToPlaceResponseModelConverter(){
        return new PlaceToPlaceResponseModelConverter();
    }

    @Bean
    public PlaceRequestModelToPlaceConverter placeRequestModelToPlaceConverter(){
        return new PlaceRequestModelToPlaceConverter();
    }

    @Bean
    public DictionaryToDictionaryResponseModelConverter dictionaryToDictionaryResponseModelConverter(){
        return new DictionaryToDictionaryResponseModelConverter();
    }

    @Bean
    public DictionaryDefinitionRequestModelToDictionaryDefinitionConverter dictionaryDefinitionRequestModelToDictionaryDefinitionConverter(){
        return new DictionaryDefinitionRequestModelToDictionaryDefinitionConverter();
    }

    @Bean
    public DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter(DictionaryDefinitionDao dictionaryDefinitionDao){
        return new DictionaryValueModelToDictionaryValuesConverter(dictionaryDefinitionDao);
    }
}
