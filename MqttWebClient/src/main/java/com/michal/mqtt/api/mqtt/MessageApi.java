package com.michal.mqtt.api.mqtt;

import com.michal.dao.api.RecivedMessageDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.dao.model.mqttdata.RecivedMessage;
import com.michal.dao.model.mqttdata.SendMessage;
import com.michal.mqtt.api.converter.response.RecivedMessageToRecivedMessageResponseConverter;
import com.michal.mqtt.api.converter.response.SendMessageToSendMessageResponseConverter;
import com.michal.mqtt.api.mqtt.model.request.MessageRequestModel;
import com.michal.mqtt.api.mqtt.model.response.RecivedMessagesResponseModel;
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

@RestController
@RequestMapping("/message")
public class MessageApi {

    private MqttApplication mqttApplication;
    private RecivedMessageToRecivedMessageResponseConverter recivedMessageToRecivedMessageResponseConverter;
    private SendMessageToSendMessageResponseConverter sendMessageToSendMessageResponseConverter;
    private RecivedMessageDao recivedMessageDao;
    private SendMessageDao sendMessageDao;

    public MessageApi(MqttApplication mqttApplication, RecivedMessageToRecivedMessageResponseConverter recivedMessageToRecivedMessageResponseConverter, SendMessageToSendMessageResponseConverter sendMessageToSendMessageResponseConverter, RecivedMessageDao recivedMessageDao, SendMessageDao sendMessageDao) {
        this.mqttApplication = mqttApplication;
        this.recivedMessageToRecivedMessageResponseConverter = recivedMessageToRecivedMessageResponseConverter;
        this.sendMessageToSendMessageResponseConverter = sendMessageToSendMessageResponseConverter;
        this.recivedMessageDao = recivedMessageDao;
        this.sendMessageDao = sendMessageDao;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public ResponseEntity<SimpleResponse> publishMessage(@Valid @RequestBody MessageRequestModel messageRequestModel) throws MqttException {
        MqttClientImpl client = mqttApplication.getByBrokerId(messageRequestModel.getBrokerId());
        client.publish(messageRequestModel.getTopic(), messageRequestModel.getMessage(), 0);
        return new ResponseEntity<>(SimpleResponse.create("MessageRequestModel to topic: " + messageRequestModel.getTopic() + " published"), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/recived")
    public ResponseEntity<List<RecivedMessagesResponseModel>> getAllRecivedMessages() throws MqttException {
        List<RecivedMessage> recivedMessages = recivedMessageDao.getAll();
        return new ResponseEntity<>(recivedMessageToRecivedMessageResponseConverter.convert(recivedMessages), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/send")
    public ResponseEntity<List<SendMessagesResponseModel>> getAllSendMessages() throws MqttException {
        List<SendMessage> sendMessages = sendMessageDao.getAll();
        return new ResponseEntity<>(sendMessageToSendMessageResponseConverter.convert(sendMessages), HttpStatus.OK);
    }

}
