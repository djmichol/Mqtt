package com.michal.mqtt.callback.topic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PrintMessageCallback extends MessageListenerAbstract {

	final Logger logger = LogManager.getLogger(PrintMessageCallback.class);

	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		logger.info("MessageRequestModel arrived succesfull!!! {}", "topic: " + arg0 + "; message: " + new String(message.getPayload()));
	}

}
