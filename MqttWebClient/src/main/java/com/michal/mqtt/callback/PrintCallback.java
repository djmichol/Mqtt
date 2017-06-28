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
		logger.info("MessageRequestModel published succesfull!!!");
	}

	@Override
	public void messageArrived(String arg0, MqttMessage message) throws Exception {
		logger.info("MessageRequestModel arrived succesfull!!! {}", "topic: " + arg0 + "; message: " + new String(message.getPayload()));
	}

}
