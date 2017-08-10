package com.michal.mqtt;

import java.io.Serializable;
import java.util.Date;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.ReceivedMessageDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.dao.model.mqttdata.SendMessage;
import com.michal.mqtt.engine.client.DataBaseCallback;
import com.michal.mqtt.engine.client.ReceivedMessageExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.michal.dao.model.networkstructure.Broker;

public class MqttClientImpl implements Serializable {

    final Logger logger = LogManager.getLogger(MqttClientImpl.class);

    private SendMessageDao sendMessageDao;
    private ReceivedMessageDao receivedMessageDao;
    private ReceivedMessageExtractor receivedMessageExtractor;
    private BrokerDao brokerDao;
    private MqttClient client;
    private MqttConnectOptions connectionOptions;
    private Broker broker;

    public MqttClientImpl(Broker broker, String clientId, SendMessageDao sendMessageDao, ReceivedMessageDao receivedMessageDao, ReceivedMessageExtractor receivedMessageExtractor,
                          BrokerDao brokerDao) throws MqttException {
        this.broker = broker;
        this.sendMessageDao = sendMessageDao;
        this.receivedMessageDao = receivedMessageDao;
        this.receivedMessageExtractor = receivedMessageExtractor;
        this.brokerDao = brokerDao;
        client = new MqttClient(broker.getUrl(), clientId, new MemoryPersistence());
        initConnectionOptions(broker);
    }

    private void initConnectionOptions(Broker broker) {
        connectionOptions = new MqttConnectOptions();
        connectionOptions.setCleanSession(true);
        connectionOptions.setAutomaticReconnect(true);
        connectionOptions.setUserName(broker.getUser());
        connectionOptions.setPassword(broker.getPassword().toCharArray());
    }

    public boolean connect() throws MqttException {
        logger.info("mqtt-client connecting to broker: " + client.getServerURI());
        client.connect(connectionOptions);
        client.setCallback(new DataBaseCallback(this, receivedMessageDao, brokerDao, receivedMessageExtractor));
        brokerDao.updateStatus(broker.getId(), new Date(), "connected");
        logger.info("mqtt-client connected");
        subscribeAllTopics();
        return true;
    }

    private void subscribeAllTopics() throws MqttException {
        subscribeTopic("#");
    }

    public boolean subscribeTopic(String topic) throws MqttException {
        client.subscribe(topic, 0);
        logger.info("Subscribed topic '{}'", topic);
        return true;
    }

    public boolean unsubscribeTopic(String topic) throws MqttException {
        client.unsubscribe(topic);
        logger.info("Unsubscribed topic '{}'", topic);
        return true;
    }

    public void publish(String topic, String message, int pubQoS) throws MqttException {
        MqttMessage messageToPublish = getMqttMessageToPublish(message, pubQoS);
        logSendMessageToDB(topic, message);
        client.publish(topic, messageToPublish);
    }

    private MqttMessage getMqttMessageToPublish(String message, int pubQoS) {
        MqttMessage messageToPublish = new MqttMessage(message.getBytes());
        messageToPublish.setQos(pubQoS);
        messageToPublish.setRetained(false);
        return messageToPublish;
    }

    private void logSendMessageToDB(String topic, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMessage(message);
        sendMessage.setBroker(this.broker);
        sendMessage.setTopic(topic);
        sendMessage.setTimestamp(new Date());
        sendMessageDao.create(sendMessage);
    }

    public boolean disconnect() throws MqttException {
        client.disconnect();
        brokerDao.updateStatus(broker.getId(), new Date(), "disconnected");
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
