package com.michal.mqtt.api.converter.response;

import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.model.response.ClientResponseModel;

import java.util.stream.Collectors;

public class MqttClientToClientModelConverter extends Converter<MqttClientImpl, ClientResponseModel> {

    private TopicToTopicModelConverter topicToTopicModelConverter;

    public MqttClientToClientModelConverter(TopicToTopicModelConverter topicToTopicModelConverter) {
        this.topicToTopicModelConverter = topicToTopicModelConverter;
    }

    @Override
    public ClientResponseModel convert(MqttClientImpl mqttClient) {
        ClientResponseModel clientResponseModel = new ClientResponseModel();
        if (mqttClient.getBroker() != null) {
            clientResponseModel.setBrokerId(mqttClient.getBroker().getId());
            clientResponseModel.setBrokerUrl(mqttClient.getBroker().getUri());
            clientResponseModel.setBrokerUser(mqttClient.getBroker().getUser());
            clientResponseModel.setBrokerCallback(mqttClient.getBroker().getCallbackEnum());
            clientResponseModel.setConnected(mqttClient.isConnected());
            if (mqttClient.getBroker().getTopics() != null) {
                clientResponseModel.setTopics(topicToTopicModelConverter.convert(mqttClient.getBroker().getTopics().stream().collect(Collectors.toList())));
            }
        }
        return clientResponseModel;
    }
}
