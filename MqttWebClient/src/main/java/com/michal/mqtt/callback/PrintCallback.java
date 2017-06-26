package com.michal.mqtt.callback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.michal.mqtt.MqttClientImpl;

public class PrintCallback extends MqttCallbackAbstract {

	final Logger logger = LogManager.getLogger(PrintCallback.class);

	public PrintCallback(MqttClientImpl client) {
		super(client);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		logger.info("MessageModel published succesfull!!!");
	}

	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		logger.info("MessageModel arrived succesfull!!! {}", "topic: " + arg0 + "; message: " + new String(message.getPayload()));
	}

}
