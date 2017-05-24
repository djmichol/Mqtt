package com.michal.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.michal.dao.BrokerDao;
import com.michal.model.Broker;
import com.michal.mqtt.MqttClientImpl;

@Component
@Scope("singleton")
public class Application {

	final static Logger logger = LogManager.getLogger(Application.class);

	@Autowired
	private BrokerDao repository;

	private List<MqttClientImpl> brokersClient;
	public static final String CLIENT_ID = "ServerClient";
	
	@PostConstruct
	public void init(){
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
