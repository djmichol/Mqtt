package com.michal.mqtt.api.converter.response;

import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.response.ClientResponseModel;

import java.util.stream.Collectors;

public class MqttClientToClientModelConverter extends Converter<MqttClientImpl, ClientResponseModel> {

    private NodeToNodeResponseConverter nodeToNodeResponseConverter;

    public MqttClientToClientModelConverter(NodeToNodeResponseConverter nodeToNodeResponseConverter) {
        this.nodeToNodeResponseConverter = nodeToNodeResponseConverter;
    }

    @Override
    public ClientResponseModel convert(MqttClientImpl mqttClient) {
        ClientResponseModel clientResponseModel = new ClientResponseModel();
        if (mqttClient.getBroker() != null) {
            clientResponseModel.setBrokerId(mqttClient.getBroker().getId());
            clientResponseModel.setBrokerUrl(mqttClient.getBroker().getUrl());
            clientResponseModel.setBrokerUser(mqttClient.getBroker().getUser());
            clientResponseModel.setStatus(mqttClient.getBroker().getStatus());
            clientResponseModel.setName(mqttClient.getBroker().getName());
            clientResponseModel.setStatusSince(mqttClient.getBroker().getStatusSince());
            if (mqttClient.getBroker().getNodes() != null) {
                clientResponseModel.setNodes(nodeToNodeResponseConverter.convert(mqttClient.getBroker().getNodes().stream().collect(Collectors.toList())));
            }
        }
        return clientResponseModel;
    }
}
