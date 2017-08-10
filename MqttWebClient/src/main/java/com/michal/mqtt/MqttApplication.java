package com.michal.mqtt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.michal.dao.api.ReceivedMessageDao;
import com.michal.dao.api.SendMessageDao;
import com.michal.mqtt.engine.client.ReceivedMessageExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.michal.dao.api.BrokerDao;
import com.michal.dao.model.networkstructure.Broker;

public class MqttApplication {

    final static Logger logger = LogManager.getLogger(MqttApplication.class);

    private BrokerDao repository;
    private SendMessageDao sendMessageDao;
    private ReceivedMessageDao receivedMessageDao;
    private ReceivedMessageExtractor receivedMessageExtractor;

    public MqttApplication(BrokerDao repository, SendMessageDao sendMessageDao, ReceivedMessageDao receivedMessageDao, ReceivedMessageExtractor receivedMessageExtractor) {
        this.repository = repository;
        this.sendMessageDao = sendMessageDao;
        this.receivedMessageDao = receivedMessageDao;
        this.receivedMessageExtractor = receivedMessageExtractor;
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
        brokers.forEach(broker -> {
            try {
                MqttClientImpl mqttClient = new MqttClientImpl(broker, CLIENT_ID, sendMessageDao, receivedMessageDao, receivedMessageExtractor, repository);
                tryToConnectToBroker(broker, mqttClient);
                brokersClient.add(mqttClient);
            } catch (MqttException e) {
                repository.updateStatus(broker.getId(), new Date(), "connection error: " + e.getMessage());
                logger.error(e);
            }
        });
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

    public Optional<MqttClientImpl> getByBrokerId(Long id) {
        List<MqttClientImpl> brokers = getBrokers();
        return brokers.stream().filter(mqttClient -> mqttClient.getBroker().getId().equals(id)).findFirst();
    }
}
