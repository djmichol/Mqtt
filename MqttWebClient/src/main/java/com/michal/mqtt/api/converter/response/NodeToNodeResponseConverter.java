package com.michal.mqtt.api.converter.response;


import com.michal.dao.model.networkstructure.Node;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.response.NodeResponseModel;

import java.util.stream.Collectors;

public class NodeToNodeResponseConverter extends Converter<Node, NodeResponseModel> {

    private SensorToSensorDetailsResponseConverter sensorToSensorResponseConverter;

    public NodeToNodeResponseConverter(SensorToSensorDetailsResponseConverter sensorToSensorResponseConverter) {
        this.sensorToSensorResponseConverter = sensorToSensorResponseConverter;
    }

    @Override
    public NodeResponseModel convert(Node node) {
        if (node != null) {
            NodeResponseModel nodeResponseModel = new NodeResponseModel();
            nodeResponseModel.setBrokerId(node.getBroker().getId());
            nodeResponseModel.setBrokerName(node.getBroker().getName());
            nodeResponseModel.setId(node.getId());
            nodeResponseModel.setLastSeen(node.getLastSeen());
            nodeResponseModel.setName(node.getName());
            nodeResponseModel.setStatus(node.getStatus());
            nodeResponseModel.setUrl(node.getUrl());
            nodeResponseModel.setSensors(sensorToSensorResponseConverter.convert(node.getSensors().stream().collect(Collectors.toList())));
            return nodeResponseModel;
        }
        return null;
    }
}
