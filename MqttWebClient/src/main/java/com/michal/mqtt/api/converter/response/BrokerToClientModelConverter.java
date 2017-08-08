package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.networkstructure.Broker;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.response.ClientResponseModel;

import java.util.stream.Collectors;

public class BrokerToClientModelConverter extends Converter<Broker, ClientResponseModel> {

    private NodeToNodeResponseConverter nodeToNodeResponseConverter;

    public BrokerToClientModelConverter(NodeToNodeResponseConverter nodeToNodeResponseConverter) {
        this.nodeToNodeResponseConverter = nodeToNodeResponseConverter;
    }

    @Override
    public ClientResponseModel convert(Broker broker) {
        ClientResponseModel clientResponseModel = new ClientResponseModel();
        if (broker != null) {
            clientResponseModel.setBrokerId(broker.getId());
            clientResponseModel.setBrokerUrl(broker.getUrl());
            clientResponseModel.setBrokerUser(broker.getUser());
            clientResponseModel.setStatus(broker.getStatus());
            clientResponseModel.setName(broker.getName());
            clientResponseModel.setStatusSince(broker.getStatusSince());
            if (broker.getNodes() != null) {
                clientResponseModel.setNodes(nodeToNodeResponseConverter.convert(broker.getNodes().stream().collect(Collectors.toList())));
            }
        }
        return clientResponseModel;
    }
}
