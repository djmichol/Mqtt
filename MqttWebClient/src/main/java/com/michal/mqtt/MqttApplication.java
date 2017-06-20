package com.michal.mqtt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.michal.dao.BrokerDao;
import com.michal.dao.model.Broker;

public class MqttApplication {

    final static Logger logger = LogManager.getLogger(MqttApplication.class);

    private BrokerDao repository;

    public MqttApplication(BrokerDao repository) {
        this.repository = repository;
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
                brokersClient.add(new MqttClientImpl(broker, CLIENT_ID));
            } catch (MqttException e) {
                logger.error(e);
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
