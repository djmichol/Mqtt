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
		logger.info("MessageRequestModel published succesfull!!!");
	}

	/**
	 * message in format home/kitchen/temperature or outdoor//tepmerature
	 */
	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String messagePayload = new String(message.getPayload());
		SensorData data = perpareData(messagePayload, topic);
		dataRepo.create(data);
	}

	private static final int PLACE=0;
	private static final int ROOM=1;
	private static final int TOPIC=2;
	private SensorData perpareData(String messagePayload, String topic) {
		String[] topicElements = topic.split("/");
		SensorData data = new SensorData();
		data.setDataPlace(topicElements[PLACE]);
		data.setDataRoom(topicElements[ROOM]);
		data.setDataType(topicElements[TOPIC]);
		data.setTopic(topic);
		data.setDataTimestamp(new Date(System.currentTimeMillis()));
		data.setSensorData(messagePayload);
		return data;
	}

}
