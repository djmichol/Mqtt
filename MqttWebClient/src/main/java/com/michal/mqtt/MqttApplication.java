package com.michal.mqtt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import com.michal.dao.api.RecivedMessageDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.mqtt.engine.client.RecivedMessageExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.model.networkstructure.Broker;

public class MqttApplication {

    final static Logger logger = LogManager.getLogger(MqttApplication.class);

    private BrokerDao repository;
    private SendMessageDao sendMessageDao;
    private RecivedMessageDao recivedMessageDao;
    private RecivedMessageExtractor recivedMessageExtractor;

    public MqttApplication(BrokerDao repository, SendMessageDao sendMessageDao, RecivedMessageDao recivedMessageDao, RecivedMessageExtractor recivedMessageExtractor) {
        this.repository = repository;
        this.sendMessageDao = sendMessageDao;
        this.recivedMessageDao = recivedMessageDao;
        this.recivedMessageExtractor = recivedMessageExtractor;
    }

    private List<MqttClientImpl> brokersClient;
    public static final String CLIENT_ID = "ServerClient";

    @PostConstruct
    public void init() {
        if (brokersClient == null) {
            prepareBrokers(repository.getAllBrokers());
        }
    }

    private void prepareBrokers(List<Broker> brokers) {
        brokersClient = new ArrayList<>();
        for (Broker broker : brokers) {
            try {
                MqttClientImpl mqttClient = new MqttClientImpl(broker, CLIENT_ID, sendMessageDao, recivedMessageDao, recivedMessageExtractor, repository);
                tryToConnectToBroker(broker, mqttClient);
                brokersClient.add(mqttClient);
            } catch (MqttException e) {
                repository.updateStatus(broker.getId(), new Date(), "connection error: " + e.getMessage());
                logger.error(e);
            }
        }
    }

    private void tryToConnectToBroker(Broker broker, MqttClientImpl mqttClient) throws MqttException {
        if (!mqttClient.isConnected()) {
            if (!mqttClient.connect()) {
                repository.updateStatus(broker.getId(), new Date(), "disconnected");
            }
        }
    }

    public List<MqttClientImpl> getBrokers() {
        return brokersClient;
    }

    public MqttClientImpl getByBrokerId(Long id) {
        List<MqttClientImpl> brokers = getBrokers();
        for (MqttClientImpl broker : brokers) {
            if (broker.getBroker().getId().equals(id)) {
                return broker;
            }
        }
        return null;
    }
}
