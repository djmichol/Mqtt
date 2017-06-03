package com.michal.mqtt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michal.mqtt.Application;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.rest.model.Message;

@RestController
@RequestMapping("/message")
public class MessageApi {

	@Autowired
	private Application application;

	@RequestMapping(method = RequestMethod.POST, value = "/publish/brokerId={brokerId}")
	public ResponseEntity<String> publishMessage(@RequestBody Message message) {
		MqttClientImpl client = application.getByBrokerId(message.getBrokerId());
		if (client != null) {
			client.publish(message.getTopic().getTopic(), message.getMessage(), 0);
			return new ResponseEntity<String>("Message to topic: " + message.getTopic() + " published", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No MQTT client found", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
