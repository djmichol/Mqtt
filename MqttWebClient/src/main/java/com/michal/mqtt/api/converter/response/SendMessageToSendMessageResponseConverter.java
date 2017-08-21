package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.mqttdata.SendMessage;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.mqtt.model.response.SendMessagesResponseModel;
import com.michal.mqtt.api.networkstructure.ClientsApi;
import com.michal.mqtt.api.networkstructure.SensorsApi;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class SendMessageToSendMessageResponseConverter extends ResponseConverter<SendMessage, SendMessagesResponseModel> {
    @Override
    public SendMessagesResponseModel convert(SendMessage sendMessage) {
        if(sendMessage!=null){
            SendMessagesResponseModel sendMessagesResponseModel = new SendMessagesResponseModel();
            sendMessagesResponseModel.setDataTimestamp(sendMessage.getTimestamp());
            sendMessagesResponseModel.setMessage(sendMessage.getMessage());
            sendMessagesResponseModel.setTopic(sendMessage.getTopic());
            prepareLinks(sendMessage, sendMessagesResponseModel);
            return sendMessagesResponseModel;
        }
        return null;
    }

    @Override
    protected void prepareLinks(SendMessage sendMessage, SendMessagesResponseModel sendMessagesResponseModel) {
        Link broker = linkTo(methodOn(ClientsApi.class).getBrokerDetails(sendMessage.getBroker().getId())).withRel("sendMessage.broker");
        sendMessagesResponseModel.add(broker);
    }
}
