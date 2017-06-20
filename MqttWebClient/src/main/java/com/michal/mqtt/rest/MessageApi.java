package com.michal.mqtt.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.mqtt.MqttApplication;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.rest.model.Message;

@RestController
@RequestMapping("/message")
public class MessageApi {

    private MqttApplication mqttApplication;

    public MessageApi(MqttApplication mqttApplication) {
        this.mqttApplication = mqttApplication;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publish")
    public ResponseEntity<String> publishMessage(@RequestBody Message message) {
        MqttClientImpl client = mqttApplication.getByBrokerId(message.getBrokerId());
        if (client != null) {
            client.publish(message.getTopic(), message.getMessage(), 0);
            return new ResponseEntity<>("Message to topic: " + message.getTopic() + " published", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No MQTT client found", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
