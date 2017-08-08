package com.michal.mqtt.callback.client;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.api.RecivedMessageDao;
import com.michal.dao.model.mqttdata.RecivedMessage;
import com.michal.mqtt.MqttClientImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

public class DataBaseCallback extends MqttCallbackAbstract {

    private RecivedMessageDao recivedMessageDao;
    private BrokerDao brokerDao;
    private RecivedMessageExtractor recivedMessageExtractor;

    public DataBaseCallback(MqttClientImpl client, RecivedMessageDao recivedMessageDao, BrokerDao brokerDao, RecivedMessageExtractor recivedMessageExtractor) {
        super(brokerDao, client);
        this.recivedMessageDao = recivedMessageDao;
        this.brokerDao = brokerDao;
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
        recivedMessageExtractor.topic(topic).extractSensor().saveMessage(new String(message.getPayload(), "UTF-8"));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
