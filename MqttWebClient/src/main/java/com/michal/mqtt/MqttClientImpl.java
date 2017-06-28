package com.michal.mqtt;

import java.io.Serializable;

import com.michal.mqtt.callback.client.PrintCallback;
import com.michal.mqtt.callback.topic.CallbackFactory;
import com.michal.mqtt.callback.topic.MessageListenerAbstract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.michal.dao.model.Broker;

public class MqttClientImpl implements Serializable {

    private static final long serialVersionUID = 7190209816225844141L;

    final Logger logger = LogManager.getLogger(MqttClientImpl.class);

    private MqttClient client;
    private MqttConnectOptions connectionOptions;
    private Broker broker;

    public MqttClientImpl(Broker broker, String clientId) throws MqttException {
        this.broker = broker;
        client = new MqttClient(broker.getUri(), clientId, new MemoryPersistence());
        connectionOptions = new MqttConnectOptions();
        connectionOptions.setCleanSession(true);
        connectionOptions.setAutomaticReconnect(true);
        connectionOptions.setUserName(broker.getUser());
        connectionOptions.setPassword(broker.getPassword().toCharArray());
    }

    public boolean connect() throws MqttException {
        logger.info("mqtt-client connecting to broker: " + client.getServerURI());
        client.connect(connectionOptions);
        client.setCallback(new PrintCallback(this));
        logger.info("mqtt-client connected");
        return true;

    }

    public boolean subscribeTopic(String topic, MessageListenerAbstract messageListener) throws MqttException {
        client.subscribe(topic, 0, messageListener);
        logger.info("Subscribed topic '{}'", topic);
        return true;
    }

    public boolean unsubscribeTopic(String topic) throws MqttException {
        client.unsubscribe(topic);
        logger.info("Unsubscribed topic '{}'", topic);
        return true;
    }

    public void publish(String topic, String message, int pubQoS) throws MqttException {
        MqttMessage messageToPublish = new MqttMessage(message.getBytes());
        messageToPublish.setQos(pubQoS);
        messageToPublish.setRetained(false);
        client.publish(topic, messageToPublish);
    }

    public boolean disconnect() throws MqttException {
        client.disconnect();
        logger.info("mqtt-client disconnected");
        return true;
    }

    public Broker getBroker() {
        return broker;
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public MqttClient getClient() {
        return client;
    }
}
