package com.michal.config;

import com.michal.dao.BrokerDao;
import com.michal.dao.DictionaryDefinitionDao;
import com.michal.dao.DictionaryValuesDao;
import com.michal.dao.NotificationDao;
import com.michal.dao.PlacesDao;
import com.michal.dao.SensorDataDao;
import com.michal.dao.TopicDao;
import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.api.DictionaryApi;
import com.michal.mqtt.api.NotificationsApi;
import com.michal.mqtt.api.PlacesApi;
import com.michal.mqtt.api.converter.request.BrokerModelToBrokerConverter;
import com.michal.mqtt.api.converter.request.DictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
import com.michal.mqtt.api.converter.request.DictionaryValueModelToDictionaryValuesConverter;
import com.michal.mqtt.api.converter.request.PlaceRequestModelToPlaceConverter;
import com.michal.mqtt.api.converter.response.DictionaryToDictionaryResponseModelConverter;
import com.michal.mqtt.api.converter.response.NotificationToNotificationModelConverter;
import com.michal.mqtt.api.converter.response.PlaceToPlaceResponseModelConverter;
import com.michal.mqtt.callback.topic.CallbackFactory;
import com.michal.mqtt.error.ExceptionController;
import com.michal.mqtt.api.MessageApi;
import com.michal.mqtt.api.ClientsApi;
import com.michal.mqtt.api.SensorDataApi;
import com.michal.mqtt.api.TopicsApi;
import com.michal.mqtt.api.converter.response.MqttClientToClientModelConverter;
import com.michal.mqtt.api.converter.response.SensorDataModelConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApiConfigurtaion {

    @Bean
    @Scope("singleton")
    public MqttApplication mqttApplication(BrokerDao brokerRepo) {
        return new MqttApplication(brokerRepo);
    }

    @Bean
    public MessageApi messageApi(MqttApplication mqttApplication) {
        return new MessageApi(mqttApplication);
    }

    @Bean
    public SensorDataApi sensorDataApi(SensorDataDao sensorDataDao, SensorDataModelConverter sensorDataModelConverter) {
        return new SensorDataApi(sensorDataDao, sensorDataModelConverter);
    }

    @Bean
    public TopicsApi topicsApi(TopicDao topicRepo, MqttApplication mqttApplication, CallbackFactory callbackFactory) {
        return new TopicsApi(topicRepo, mqttApplication, callbackFactory);
    }

    @Bean
    public ClientsApi clientsApi(MqttApplication mqttApplication, BrokerDao brokerRepo, MqttClientToClientModelConverter mqttClientToClientModelConverter,
                                 BrokerModelToBrokerConverter brokerModelToBrokerConverter) {
        return new ClientsApi(mqttApplication, brokerRepo, mqttClientToClientModelConverter, brokerModelToBrokerConverter);
    }

    @Bean
    public NotificationsApi notificationsApi(NotificationDao notificationDao, NotificationToNotificationModelConverter notificationToNotificationModelConverter) {
        return new NotificationsApi(notificationDao, notificationToNotificationModelConverter);
    }

    @Bean
    public ExceptionController exceptionController() {
        return new ExceptionController();
    }

    @Bean
    public PlacesApi placesApi(PlacesDao placesDao, PlaceToPlaceResponseModelConverter placeToPlaceResponseModelConverter, PlaceRequestModelToPlaceConverter
            placeRequestModelToPlaceConverter) {
        return new PlacesApi(placesDao, placeToPlaceResponseModelConverter, placeRequestModelToPlaceConverter);
    }

    @Bean
    public DictionaryApi dictionaryApi(DictionaryValuesDao dictionaryValuesDao, DictionaryDefinitionDao dictionaryDefinitionDao, DictionaryToDictionaryResponseModelConverter
            dictionaryToDictionaryResponseModelConverter, DictionaryDefinitionRequestModelToDictionaryDefinitionConverter
            dictionaryDefinitionRequestModelToDictionaryDefinitionConverter, DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter) {
        return new DictionaryApi(dictionaryValuesDao, dictionaryDefinitionDao, dictionaryToDictionaryResponseModelConverter,
                dictionaryDefinitionRequestModelToDictionaryDefinitionConverter, dictionaryValueModelToDictionaryValuesConverter);
    }

}
