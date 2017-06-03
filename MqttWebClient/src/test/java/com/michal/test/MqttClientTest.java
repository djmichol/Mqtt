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

import com.michal.config.DataBaseConfig;
import com.michal.config.MqttApplicationContext;
import com.michal.config.MqqtApplicationConfiguration;
import com.michal.model.Broker;
import com.michal.mqtt.MqttClientImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MqttApplicationContext.class, MqqtApplicationConfiguration.class, DataBaseConfig.class})
@WebAppConfiguration
public class MqttClientTest {

	private String URL="tcp://localhost:1883";
	private String USER="guest";
	private String PASSWORD="guest";
	//@Ignore
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
