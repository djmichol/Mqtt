package com.michal.mqtt.callback.client;

import com.michal.dao.api.BrokerDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.michal.mqtt.MqttClientImpl;

import java.util.Date;

public abstract class MqttCallbackAbstract implements MqttCallback {

	final Logger logger = LogManager.getLogger(MqttCallbackAbstract.class);

	private BrokerDao brokerDao;

	public MqttCallbackAbstract(BrokerDao brokerDao, MqttClientImpl client) {
		this.brokerDao = brokerDao;
		this.client = client;
	}

	@Override
	public void connectionLost(Throwable cause) {
		logger.error("Connection to Mqtt broker lost for {}", cause.getCause());
		logger.error("Reconnecting in progress ...");
		brokerDao.updateStatus(client.getBroker().getId(), new Date(), "Connection lost");
		reconnect(1000L);
	}

	private void reconnect(Long reconnectInvertal) {
		while (!client.isConnected()) {
			brokerDao.updateStatus(client.getBroker().getId(), new Date(), "Reconnecting");
			try {
				client.connect();
				Thread.sleep(reconnectInvertal);
			} catch (Exception e) {
				logger.error(e);
			}
		}
		brokerDao.updateStatus(client.getBroker().getId(), new Date(), "Connected");
	}

	public abstract void messageArrived(String topic, MqttMessage message) throws Exception;

	public abstract void deliveryComplete(IMqttDeliveryToken token);

	protected MqttClientImpl client;

}
