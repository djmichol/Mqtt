package com.michal.mqtt.api.converter.request;


import com.michal.dao.model.Broker;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.model.request.BrokerRequestModel;

public class BrokerModelToBrokerConverter extends Converter<BrokerRequestModel, Broker>{
    @Override
    public Broker convert(BrokerRequestModel brokerRequestModel) {
        Broker broker = new Broker();
        if(brokerRequestModel!=null) {
            broker.setCallbackEnum(brokerRequestModel.getCallbackEnum());
            broker.setPassword(brokerRequestModel.getPassword());
            broker.setUser(brokerRequestModel.getUser());
            broker.setUri(brokerRequestModel.getUrl());
        }
        return broker;
    }
}
