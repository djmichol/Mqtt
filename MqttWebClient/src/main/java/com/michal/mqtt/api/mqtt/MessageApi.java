package com.michal.mqtt.api.mqtt;

import com.michal.dao.api.ReceivedMessageDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.dao.model.mqttdata.ReceivedMessage;
import com.michal.dao.model.mqttdata.SendMessage;
import com.michal.mqtt.api.converter.response.ReceivedMessageToReceivedMessageResponseConverter;
import com.michal.mqtt.api.converter.response.SendMessageToSendMessageResponseConverter;
import com.michal.mqtt.api.mqtt.model.request.MessageRequestModel;
import com.michal.mqtt.api.mqtt.model.response.ReceivedMessagesResponseModel;
import com.michal.mqtt.api.mqtt.model.response.SendMessagesResponseModel;
import com.michal.mqtt.api.utils.SimpleResponse;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.MqttClientImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/message")
public class MessageApi {

    private MqttApplication mqttApplication;
    private ReceivedMessageToReceivedMessageResponseConverter receivedMessageToReceivedMessageResponseConverter;
    private SendMessageToSendMessageResponseConverter sendMessageToSendMessageResponseConverter;
    private ReceivedMessageDao receivedMessageDao;
    private SendMessageDao sendMessageDao;

    public MessageApi(MqttApplication mqttApplication, ReceivedMessageToReceivedMessageResponseConverter receivedMessageToReceivedMessageResponseConverter,
                      SendMessageToSendMessageResponseConverter sendMessageToSendMessageResponseConverter, ReceivedMessageDao receivedMessageDao, SendMessageDao sendMessageDao) {
        this.mqttApplication = mqttApplication;
        this.receivedMessageToReceivedMessageResponseConverter = receivedMessageToReceivedMessageResponseConverter;
        this.sendMessageToSendMessageResponseConverter = sendMessageToSendMessageResponseConverter;
        this.receivedMessageDao = receivedMessageDao;
        this.sendMessageDao = sendMessageDao;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public ResponseEntity<SimpleResponse> publishMessage(@Valid @RequestBody MessageRequestModel messageRequestModel) throws MqttException {
        Optional<MqttClientImpl> client = mqttApplication.getByBrokerId(messageRequestModel.getBrokerId());
        client.orElseThrow(() -> new MqttException(MqttException.REASON_CODE_BROKER_UNAVAILABLE)).publish(messageRequestModel.getTopic(), messageRequestModel.getMessage(), 0);
        return new ResponseEntity<>(SimpleResponse.create("MessageRequestModel to topic: " + messageRequestModel.getTopic() + " published"), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recived")
    public ResponseEntity<List<ReceivedMessagesResponseModel>> getAllReceivedMessages() throws MqttException {
        List<ReceivedMessage> receivedMessages = receivedMessageDao.getAll();
        return new ResponseEntity<>(receivedMessageToReceivedMessageResponseConverter.convert(receivedMessages), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/send")
    public ResponseEntity<List<SendMessagesResponseModel>> getAllSendMessages() throws MqttException {
        List<SendMessage> sendMessages = sendMessageDao.getAll();
        return new ResponseEntity<>(sendMessageToSendMessageResponseConverter.convert(sendMessages), HttpStatus.OK);
    }

}
