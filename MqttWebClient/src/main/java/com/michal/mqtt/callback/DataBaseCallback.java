package com.michal.mqtt.callback;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.michal.config.SpringApplicationContext;
import com.michal.dao.MessageDao;
import com.michal.model.Message;
import com.michal.mqtt.MqttClientImpl;

public class DataBaseCallback extends MqttCallbackAbstract {
	
	final Logger logger = LogManager.getLogger(DataBaseCallback.class);

	private MessageDao messageRepo;
	
	public DataBaseCallback(MqttClientImpl client) {
		super(client);
		messageRepo = (MessageDao) SpringApplicationContext.getBean(MessageDao.class);		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		logger.info("Message published succesfull!!!");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {		
		messageRepo.create(new Message(topic, new String(message.getPayload())));
		logger.info("Message added {}", "topic: " + topic + "; message: " + new String(message.getPayload()));
	}
}
