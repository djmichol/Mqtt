package com.michal.mqtt.api.converter.request;


import com.michal.dao.model.networkstructure.Broker;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.networkstructure.model.request.BrokerRequestModel;

public class BrokerModelToBrokerConverter extends Converter<BrokerRequestModel, Broker>{
    @Override
    public Broker convert(BrokerRequestModel brokerRequestModel) {
        Broker broker = new Broker();
        if(brokerRequestModel!=null) {
            broker.setPassword(brokerRequestModel.getPassword());
            broker.setUser(brokerRequestModel.getUser());
            broker.setUrl(brokerRequestModel.getUrl());
            broker.setName(brokerRequestModel.getName());
        }
        return broker;
    }
}
