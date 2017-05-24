package com.michal.mqtt.callback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.michal.mqtt.MqttClientImpl;

public abstract class MqttCallbackAbstract implements MqttCallback {

	final Logger logger = LogManager.getLogger(MqttCallbackAbstract.class);

	public MqttCallbackAbstract(MqttClientImpl client) {
		this.client = client;
	}

	@Override
	public void connectionLost(Throwable cause) {
		logger.error("Connection to Mqtt broker lost for {}", cause.getCause());
		logger.error("Reconnecting in progress ...");
		reconnect(1000L);
	}

	private void reconnect(Long reconnectInvertal) {
		while (!client.isConnected()) {
			try {
				client.connect();
				Thread.sleep(reconnectInvertal);
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	public abstract void messageArrived(String topic, MqttMessage message) throws Exception;

	public abstract void deliveryComplete(IMqttDeliveryToken token);

	protected MqttClientImpl client;

}
