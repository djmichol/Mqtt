package com.michal.mqtt.callback.topic;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PrintMessageCallback extends MessageListenerAbstract {

	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		logger.info("MessageRequestModel arrived succesfull!!! {}", "topic: " + arg0 + "; message: " + new String(message.getPayload()));
	}

}
