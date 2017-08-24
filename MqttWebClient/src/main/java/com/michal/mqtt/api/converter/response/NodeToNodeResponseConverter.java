package com.michal.mqtt.api.converter.response;


import com.michal.dao.model.networkstructure.Node;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.networkstructure.ClientsApi;
import com.michal.mqtt.api.networkstructure.NodesApi;
import com.michal.mqtt.api.networkstructure.SensorsApi;
import com.michal.mqtt.api.networkstructure.model.response.NodeResponseModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class NodeToNodeResponseConverter extends ResponseConverter<Node, NodeResponseModel> {

    @Override
    public NodeResponseModel convert(Node node) {
        if (node != null) {
            NodeResponseModel nodeResponseModel = new NodeResponseModel();
            nodeResponseModel.setLastSeen(node.getLastSeen());
            nodeResponseModel.setName(node.getName());
            nodeResponseModel.setStatus(node.getStatus());
            nodeResponseModel.setUrl(node.getUrl());
            prepareLinks(node, nodeResponseModel);
            return nodeResponseModel;
        }
        return null;
    }

    @Override
    protected void prepareLinks(Node node, NodeResponseModel nodeResponseModel) {
        Link sensors = linkTo(methodOn(SensorsApi.class).getSensorInNode(node.getId())).withRel("node.sensors");
        if (node.getBroker() != null) {
            Link broker = linkTo(methodOn(ClientsApi.class).getBrokerDetails(node.getBroker().getId())).withRel("node.broker");
            nodeResponseModel.add(broker);
        }
        Link detail = linkTo(methodOn(NodesApi.class).getNodeDetails(node.getId())).withSelfRel();
        nodeResponseModel.add(detail);
        nodeResponseModel.add(sensors);

    }
}
