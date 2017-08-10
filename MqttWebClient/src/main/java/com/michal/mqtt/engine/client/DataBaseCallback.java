package com.michal.mqtt.engine.client;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.RecivedMessageDao;
import com.michal.dao.model.mqttdata.RecivedMessage;
import com.michal.mqtt.MqttClientImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

public class DataBaseCallback extends MqttCallbackAbstract {

    private RecivedMessageDao recivedMessageDao;
    private RecivedMessageExtractor recivedMessageExtractor;

    public DataBaseCallback(MqttClientImpl client, RecivedMessageDao recivedMessageDao, BrokerDao brokerDao, RecivedMessageExtractor recivedMessageExtractor) {
        super(brokerDao, client);
        this.recivedMessageDao = recivedMessageDao;
        this.recivedMessageExtractor = recivedMessageExtractor;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        if (!message.isDuplicate()) {
            RecivedMessage recivedMessage = new RecivedMessage();
            recivedMessage.setMessage(new String(message.getPayload(), "UTF-8"));
            recivedMessage.setBroker(client.getBroker());
            recivedMessage.setTopic(topic);
            recivedMessage.setTimestamp(new Date());
            recivedMessageDao.create(recivedMessage);
        }
        recivedMessageExtractor
                .topic(topic)
                .message(new String(message.getPayload(), "UTF-8"))
                .extractSensor()
                .checkRules()
                .executeNotificationActions()
                .saveMessage();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
