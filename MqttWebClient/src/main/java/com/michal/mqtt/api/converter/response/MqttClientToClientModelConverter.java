package com.michal.mqtt.api.converter.response;

import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.networkstructure.ClientsApi;
import com.michal.mqtt.api.networkstructure.NodesApi;
import com.michal.mqtt.api.networkstructure.model.response.ClientResponseModel;
import org.springframework.hateoas.Link;

import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MqttClientToClientModelConverter extends ResponseConverter<MqttClientImpl, ClientResponseModel> {

    @Override
    public ClientResponseModel convert(MqttClientImpl mqttClient) {
        ClientResponseModel clientResponseModel = new ClientResponseModel();
        if (mqttClient.getBroker() != null) {
            clientResponseModel.setBrokerUrl(mqttClient.getBroker().getUrl());
            clientResponseModel.setBrokerUser(mqttClient.getBroker().getUser());
            clientResponseModel.setStatus(mqttClient.getBroker().getStatus());
            clientResponseModel.setName(mqttClient.getBroker().getName());
            clientResponseModel.setStatusSince(mqttClient.getBroker().getStatusSince());
            prepareLinks(mqttClient, clientResponseModel);
        }
        return clientResponseModel;
    }

    @Override
    protected void prepareLinks(MqttClientImpl mqttClient, ClientResponseModel clientResponseModel) {
        Link nodes = linkTo(methodOn(NodesApi.class).getNodesByBrokerId(mqttClient.getBroker().getId())).withRel("client.nodes");
        Link detail = linkTo(methodOn(ClientsApi.class).getBrokerDetails(mqttClient.getBroker().getId())).withSelfRel();
        clientResponseModel.add(detail);
        clientResponseModel.add(nodes);
    }
}
