package com.michal.mqtt;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClientImpl implements MqttCallback {

	final Logger logger = LogManager.getLogger(MqttClientImpl.class);

	private MqttClient client;
	private MqttConnectOptions connectionOptions;

	public MqttClientImpl(String broker, String clientId, String userName, String password) throws MqttException {
		Configurator.setLevel(logger.getName(), Level.DEBUG);
		client = new MqttClient(broker, clientId, new MemoryPersistence());
		connectionOptions = new MqttConnectOptions();
		connectionOptions.setCleanSession(true);
		connectionOptions.setAutomaticReconnect(true);
		connectionOptions.setUserName(userName);
		connectionOptions.setPassword(password.toCharArray());
	}

	public void connect() {
		logger.info("mqtt-client connecting to broker: " + client.getServerURI());
		try {
			client.connect(connectionOptions);
		} catch (MqttSecurityException e) {
			logger.error("mqtt-client connecting to broker: " + client.getServerURI(),"auth problem",e.getMessage());
		} catch (MqttException e) {
			logger.error("mqtt-client connecting to broker: " + client.getServerURI()," Problem try to reconnect",e.getMessage());
			reconnect(3000L);
		}
		logger.info("mqtt-client connected");
	}

	public void subscribeTopic(String topic) {
		try {
			client.subscribe(topic, 0);
			logger.info("Subscribed topic '{}'", topic);
		} catch (MqttException ex) {
			logger.error("Unable to subscribe topic '{}' for {}", topic, ex);
		}
	}
	
	public void unsubscribeTopic(String topic) {
		try {
			client.unsubscribe(topic);
			logger.info("Unsubscribed topic '{}'", topic);
		} catch (MqttException ex) {
			logger.error("Unable to unsubscribed topic '{}' for {}", topic, ex);
		}
	}

	public void publish(String topic, String message, int pubQoS) {
		MqttMessage messageToPublish = new MqttMessage(message.getBytes());
		messageToPublish.setQos(pubQoS);
		messageToPublish.setRetained(false);
		try {
			client.publish(topic, messageToPublish);
		} catch (MqttException ex) {
			logger.error("Unable to publish message: '" + message + "' to " + topic + " for " + ex.getMessage());
		}
	}

	public void disconnect() throws MqttException {
		client.disconnect();
		logger.info("mqtt-client disconnected");
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
				client.connect(connectionOptions);
			} catch (MqttException e) {
				logger.error("Unable to connect to broker {}", client.getServerURI() + " for " + e.getMessage());
			}
			try {
				Thread.sleep(reconnectInvertal);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		logger.info("Message published succesfull!!!");
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		logger.info("Message arrived succesfull!!!","topic:",arg0,"message",arg1.getPayload());
	}

	public MqttClient getClient() {
		return client;
	}
}
