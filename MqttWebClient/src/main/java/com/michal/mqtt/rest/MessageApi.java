package com.michal.mqtt.rest;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.rest.model.Message;

import javax.validation.Valid;

@RestController
@RequestMapping("/message")
public class MessageApi {

    private MqttApplication mqttApplication;

    public MessageApi(MqttApplication mqttApplication) {
        this.mqttApplication = mqttApplication;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public ResponseEntity<String> publishMessage(@Valid @RequestBody Message message) throws MqttException {
        MqttClientImpl client = mqttApplication.getByBrokerId(message.getBrokerId());
        client.publish(message.getTopic(), message.getMessage(), 0);
        return new ResponseEntity<>("Message to topic: " + message.getTopic() + " published", HttpStatus.OK);
    }

}
