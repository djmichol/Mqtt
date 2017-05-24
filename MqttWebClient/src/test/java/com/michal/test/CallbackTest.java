package com.michal.test;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.michal.config.AppConfig;
import com.michal.dao.SensorDataDao;
import com.michal.model.Broker;
import com.michal.mqtt.MqttClientImpl;
import com.michal.mqtt.callback.SensorDataCallback;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CallbackTest {

	@Autowired
	private SensorDataDao sensorRepo;
	
	@Test
	public void messageArrivedExpectedNewDataElement() throws Exception{
		int sizeBefore = sensorRepo.getDataByType("temperature").size();
		SensorDataCallback callback = new SensorDataCallback(new MqttClientImpl(new Broker("tcp://10.132.221.251:1883","home2","home2"),"test"));		
		callback.messageArrived("home/livingroom/temperature", new MqttMessage("32".getBytes()));
		int sizeAfter = sensorRepo.getDataByType("temperature").size();
		Assert.assertEquals(sizeBefore+1, sizeAfter);
	}
	
}
