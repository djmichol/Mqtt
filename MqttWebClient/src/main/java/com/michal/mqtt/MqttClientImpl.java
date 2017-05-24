package com.michal.mqtt;

import java.beans.Transient;
import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.michal.model.Broker;
import com.michal.mqtt.callback.DataBaseCallback;

public class MqttClientImpl implements Serializable {

	private static final long serialVersionUID = 7190209816225844141L;

	final Logger logger = LogManager.getLogger(MqttClientImpl.class);

	private transient MqttClient client;
	private transient MqttConnectOptions connectionOptions;
	private Broker broker;

	public MqttClientImpl(Broker broker, String clientId) throws MqttException{
		this.broker = broker;
		client = new MqttClient(broker.getUri(), clientId, new MemoryPersistence());
		connectionOptions = new MqttConnectOptions();
		connectionOptions.setCleanSession(true);
		connectionOptions.setAutomaticReconnect(true);
		connectionOptions.setUserName(broker.getUser());
		connectionOptions.setPassword(broker.getPassword().toCharArray());
	}

	public boolean connect() {
		logger.info("mqtt-client connecting to broker: " + client.getServerURI());
		try {
			client.connect(connectionOptions);
			client.setCallback(new DataBaseCallback(this));
			logger.info("mqtt-client connected");
			return true;
		} catch (MqttSecurityException e) {
			logger.error("mqtt-client connecting to broker: " + client.getServerURI(),"auth problem",e);
			return false;
		} catch (MqttException e) {
			logger.error("mqtt-client connecting to broker: " + client.getServerURI()," Problem try to reconnect",e);
			return false;
		}		
	}

	public boolean subscribeTopic(String topic) {
		try {
			client.subscribe(topic, 0);
			logger.info("Subscribed topic '{}'", topic);
			return true;
		} catch (MqttException ex) {
			logger.error("Unable to subscribe topic '{}' for {}", topic, ex);
			return false;
		}
	}
	
	public boolean unsubscribeTopic(String topic) {
		try {
			client.unsubscribe(topic);
			logger.info("Unsubscribed topic '{}'", topic);
			return true;
		} catch (MqttException ex) {
			logger.error("Unable to unsubscribed topic '{}' for {}", topic, ex);
			return false;
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

	public boolean disconnect(){
		try {
			client.disconnect();
			logger.info("mqtt-client disconnected");
			return true;
		} catch (MqttException e) {
			logger.info("mqtt-client failed to disconnect", e);
			return false;
		}		
	}

	public Broker getBroker() {
		return broker;
	}

	public boolean isConnected() {
		return client.isConnected();
	}

	@Transient
	public MqttClient getClient() {
		return client;
	}
}
