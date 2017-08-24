package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.mqttdata.ReceivedMessage;
import com.michal.mqtt.api.converter.ResponseConverter;
import com.michal.mqtt.api.mqtt.model.response.ReceivedMessagesResponseModel;
import com.michal.mqtt.api.networkstructure.ClientsApi;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ReceivedMessageToReceivedMessageResponseConverter extends ResponseConverter<ReceivedMessage, ReceivedMessagesResponseModel> {
    @Override
    public ReceivedMessagesResponseModel convert(ReceivedMessage receivedMessage) {
        if (receivedMessage != null) {
            ReceivedMessagesResponseModel receivedMessagesResponseModel = new ReceivedMessagesResponseModel();
            receivedMessagesResponseModel.setDataTimestamp(receivedMessage.getTimestamp());
            receivedMessagesResponseModel.setMessage(receivedMessage.getMessage());
            receivedMessagesResponseModel.setTopic(receivedMessage.getTopic());
            prepareLinks(receivedMessage, receivedMessagesResponseModel);
            return receivedMessagesResponseModel;
        }
        return null;
    }

    @Override
    protected void prepareLinks(ReceivedMessage receivedMessage, ReceivedMessagesResponseModel receivedMessagesResponseModel) {
        if (receivedMessage.getBroker() != null) {
            Link broker = linkTo(methodOn(ClientsApi.class).getBrokerDetails(receivedMessage.getBroker().getId())).withRel("receivedMessage.broker");
            receivedMessagesResponseModel.add(broker);
        }
    }
}
