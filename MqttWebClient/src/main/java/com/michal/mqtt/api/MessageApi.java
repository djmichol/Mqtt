package com.michal.mqtt.api;

import com.michal.mqtt.api.model.request.MessageRequestModel;
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

@RestController
@RequestMapping("/messages")
public class MessageApi {

    private MqttApplication mqttApplication;

    public MessageApi(MqttApplication mqttApplication) {
        this.mqttApplication = mqttApplication;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public ResponseEntity<String> publishMessage(@Valid @RequestBody MessageRequestModel messageRequestModel) throws MqttException {
        MqttClientImpl client = mqttApplication.getByBrokerId(messageRequestModel.getBrokerId());
        client.publish(messageRequestModel.getTopic(), messageRequestModel.getMessage(), 0);
        return new ResponseEntity<>("MessageRequestModel to topic: " + messageRequestModel.getTopic() + " published", HttpStatus.OK);
    }

}
