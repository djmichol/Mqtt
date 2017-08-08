package com.michal.mqtt.api.converter.request;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.model.networkstructure.Node;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.request.NodeRequestModel;

public class NodeRequestToBreokerNodeConverter extends Converter<NodeRequestModel, Node> {

    private BrokerDao brokerDao;

    public NodeRequestToBreokerNodeConverter(BrokerDao brokerDao) {
        this.brokerDao = brokerDao;
    }

    @Override
    public Node convert(NodeRequestModel nodeRequestModel) {
        Node node = new Node();
        node.setBroker(brokerDao.get(nodeRequestModel.getBrokerId()));
        node.setName(nodeRequestModel.getName());
        node.setUrl(nodeRequestModel.getUrl());
        return node;
    }
}
