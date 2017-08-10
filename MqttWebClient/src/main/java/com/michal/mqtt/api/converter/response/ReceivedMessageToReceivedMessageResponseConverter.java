package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.mqttdata.ReceivedMessage;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.mqtt.model.response.ReceivedMessagesResponseModel;

public class ReceivedMessageToReceivedMessageResponseConverter extends Converter<ReceivedMessage, ReceivedMessagesResponseModel>{
    @Override
    public ReceivedMessagesResponseModel convert(ReceivedMessage receivedMessage) {
        if(receivedMessage !=null){
            ReceivedMessagesResponseModel receivedMessagesResponseModel = new ReceivedMessagesResponseModel();
            receivedMessagesResponseModel.setDataTimestamp(receivedMessage.getTimestamp());
            receivedMessagesResponseModel.setMessage(receivedMessage.getMessage());
            receivedMessagesResponseModel.setTopic(receivedMessage.getTopic());
            receivedMessagesResponseModel.setBrokerId(receivedMessage.getBroker().getId());
            receivedMessagesResponseModel.setBrokerName(receivedMessage.getBroker().getName());
            return receivedMessagesResponseModel;
        }
        return null;
    }
}
