package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.networkstructure.Broker;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.networkstructure.ClientsApi;
import com.michal.mqtt.api.networkstructure.NodesApi;
import com.michal.mqtt.api.networkstructure.model.response.ClientResponseModel;

import java.util.stream.Collectors;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BrokerToClientModelConverter extends ResponseConverter<Broker, ClientResponseModel> {

    private NodeToNodeResponseConverter nodeToNodeResponseConverter;

    public BrokerToClientModelConverter(NodeToNodeResponseConverter nodeToNodeResponseConverter) {
        this.nodeToNodeResponseConverter = nodeToNodeResponseConverter;
    }

    @Override
    public ClientResponseModel convert(Broker broker) {
        ClientResponseModel clientResponseModel = new ClientResponseModel();
        if (broker != null) {
            clientResponseModel.setBrokerUrl(broker.getUrl());
            clientResponseModel.setBrokerUser(broker.getUser());
            clientResponseModel.setStatus(broker.getStatus());
            clientResponseModel.setName(broker.getName());
            clientResponseModel.setStatusSince(broker.getStatusSince());
            prepareLinks(broker, clientResponseModel);
        }
        return clientResponseModel;
    }


    @Override
    protected void prepareLinks(Broker broker, ClientResponseModel clientResponseModel) {
        Link nodes = linkTo(methodOn(NodesApi.class).getNodesByBrokerId(broker.getId())).withRel("broker.nodes");
        Link detail = linkTo(methodOn(ClientsApi.class).getBrokerDetails(broker.getId())).withSelfRel();
        clientResponseModel.add(detail);
        clientResponseModel.add(nodes);
    }
}
