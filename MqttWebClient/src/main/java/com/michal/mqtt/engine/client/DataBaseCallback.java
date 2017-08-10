package com.michal.mqtt.engine.client;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.ReceivedMessageDao;
import com.michal.dao.model.mqttdata.ReceivedMessage;
import com.michal.mqtt.MqttClientImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class DataBaseCallback extends MqttCallbackAbstract {

    private ReceivedMessageDao receivedMessageDao;
    private ReceivedMessageExtractor receivedMessageExtractor;

    public DataBaseCallback(MqttClientImpl client, ReceivedMessageDao receivedMessageDao, BrokerDao brokerDao, ReceivedMessageExtractor receivedMessageExtractor) {
        super(brokerDao, client);
        this.receivedMessageDao = receivedMessageDao;
        this.receivedMessageExtractor = receivedMessageExtractor;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logReceivedMessageToDB(topic, message);
        receivedMessageExtractor
                .topic(topic)
                .message(getMessage(message))
                .extractSensor()
                .checkRules()
                .executeNotificationActions()
                .saveMessage();
    }

    private String getMessage(MqttMessage message) throws UnsupportedEncodingException {
        return new String(message.getPayload(), "UTF-8");
    }

    private void logReceivedMessageToDB(String topic, MqttMessage message) throws UnsupportedEncodingException {
        if (!message.isDuplicate()) {
            ReceivedMessage receivedMessage = new ReceivedMessage();
            receivedMessage.setMessage(getMessage(message));
            receivedMessage.setBroker(client.getBroker());
            receivedMessage.setTopic(topic);
            receivedMessage.setTimestamp(new Date());
            receivedMessageDao.create(receivedMessage);
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        //TODO
    }
}
