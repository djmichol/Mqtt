package com.michal.test;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.michal.config.AppConfig;
import com.michal.model.Broker;
import com.michal.mqtt.MqttClientImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class MqttClientTest {

	private String URL="tcp://10.132.221.251:1883";
	private String USER="home2";
	private String PASSWORD="home2";
	
	@Test
	public void connectionTest() throws MqttException{
		Configurator.setRootLevel(Level.DEBUG);
		Broker broker = new Broker(URL, USER, PASSWORD);
		MqttClientImpl client = new MqttClientImpl(broker,"testClient");
		client.connect();
		Assert.assertTrue(client.isConnected());
		client.disconnect();
		Assert.assertFalse(client.isConnected());
	}
	
}
