package com.michal.test;

import com.michal.mqtt.callback.CallbackEnum;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.michal.config.MqttApplicationConfiguration;
import com.michal.dao.SensorDataDao;
import com.michal.dao.model.Broker;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.callback.SensorDataCallback;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MqttApplicationConfiguration.class})
@WebAppConfiguration
public class CallbackTest {

	@Autowired
	private SensorDataDao sensorRepo;
	
	@Ignore
	@Test
	public void messageArrivedExpectedNewDataElement() throws Exception{
		int sizeBefore = sensorRepo.getDataByType("temperature").size();
		SensorDataCallback callback = new SensorDataCallback(new MqttClientImpl(new Broker("tcp://localhost:1883","home2","home2", CallbackEnum.SENSOR_DATA_CALLBACK),"test"));
		callback.messageArrived("home/livingroom/temperature", new MqttMessage("32".getBytes()));
		int sizeAfter = sensorRepo.getDataByType("temperature").size();
		Assert.assertEquals(sizeBefore+1, sizeAfter);
	}

	@Test
	public void sendMessageArrivedExpectedNewDataElement() throws Exception{
		Broker broker = new Broker("tcp://localhost:1883","home2","home2", CallbackEnum.PRINT_CALLBACK);
		MqttClientImpl client = new MqttClientImpl(broker,"testClient");
		client.connect();
		Assert.assertTrue(client.isConnected());
		client.subscribeTopic("home/livingroom/temperature");
		client.publish("home/livingroom/temperature","21",0);
		client.disconnect();
		Assert.assertFalse(client.isConnected());
	}


	
}
