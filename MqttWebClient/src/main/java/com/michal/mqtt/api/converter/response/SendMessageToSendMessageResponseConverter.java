package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.mqttdata.RecivedMessage;
import com.michal.dao.model.mqttdata.SendMessage;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.mqtt.model.response.RecivedMessagesResponseModel;
import com.michal.mqtt.api.mqtt.model.response.SendMessagesResponseModel;

public class SendMessageToSendMessageResponseConverter extends Converter<SendMessage, SendMessagesResponseModel>{
    @Override
    public SendMessagesResponseModel convert(SendMessage sendMessage) {
        if(sendMessage!=null){
            SendMessagesResponseModel sendMessagesResponseModel = new SendMessagesResponseModel();
            sendMessagesResponseModel.setDataTimestamp(sendMessage.getTimestamp());
            sendMessagesResponseModel.setMessage(sendMessage.getMessage());
            sendMessagesResponseModel.setTopic(sendMessage.getTopic());
            sendMessagesResponseModel.setBrokerId(sendMessage.getBroker().getId());
            sendMessagesResponseModel.setBrokerName(sendMessage.getBroker().getName());
            return sendMessagesResponseModel;
        }
        return null;
    }
}
