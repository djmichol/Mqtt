package com.michal.mqtt.callback;

import java.util.Date;

import com.michal.config.MqttApplicationConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.michal.dao.SensorDataDao;
import com.michal.dao.model.SensorData;
import com.michal.mqtt.MqttClientImpl;

public class SensorDataCallback extends MqttCallbackAbstract{
		
	final Logger logger = LogManager.getLogger(SensorDataCallback.class);

	private SensorDataDao dataRepo;
	
	public SensorDataCallback(MqttClientImpl client) {
		super(client);
		dataRepo = (SensorDataDao) MqttApplicationConfiguration.getBean(SensorDataDao.class);
	}
	
	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		logger.info("Message published succesfull!!!");
	}

	/**
	 * message in format home/kitchen/temperature or outdoor//tepmerature
	 */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String messagePayload = new String(message.getPayload());
		String[] topicElements = topic.split("/");		
		SensorData data = perpareData(messagePayload, topicElements);
		dataRepo.create(data);
	}

	private static final int PLACE=0;
	private static final int ROOM=1;
	private static final int TOPIC=2;
	private SensorData perpareData(String messagePayload, String[] topicElements) {
		SensorData data = new SensorData();
		data.setDataPlace(topicElements[PLACE]);
		data.setDataRoom(topicElements[ROOM]);
		data.setDataType(topicElements[TOPIC]);
		data.setDataTimestamp(new Date(System.currentTimeMillis()));
		data.setSensorData(messagePayload);
		return data;
	}

}
