package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.mqttdata.RecivedMessage;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.mqtt.model.response.RecivedMessagesResponseModel;

public class RecivedMessageToRecivedMessageResponseConverter extends Converter<RecivedMessage, RecivedMessagesResponseModel>{
    @Override
    public RecivedMessagesResponseModel convert(RecivedMessage recivedMessage) {
        if(recivedMessage!=null){
            RecivedMessagesResponseModel recivedMessagesResponseModel = new RecivedMessagesResponseModel();
            recivedMessagesResponseModel.setDataTimestamp(recivedMessage.getTimestamp());
            recivedMessagesResponseModel.setMessage(recivedMessage.getMessage());
            recivedMessagesResponseModel.setTopic(recivedMessage.getTopic());
            recivedMessagesResponseModel.setBrokerId(recivedMessage.getBroker().getId());
            recivedMessagesResponseModel.setBrokerName(recivedMessage.getBroker().getName());
            return recivedMessagesResponseModel;
        }
        return null;
    }
}
