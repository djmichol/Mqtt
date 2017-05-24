package com.michal.test;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Assert;
import org.junit.Test;

import com.michal.model.Broker;
import com.michal.mqtt.MqttClientImpl;


public class MqttClientTest {

	@Test
	public void connectionTest() throws MqttException{
		Configurator.setRootLevel(Level.DEBUG);
		Broker broker = new Broker("tcp://10.132.221.251:1883", "home2", "home2");
		MqttClientImpl client = new MqttClientImpl(broker,"michal");
		client.connect();
		Assert.assertTrue(client.isConnected());
		client.publish("home", "pierwsza wiadomosc", 2);
		client.disconnect();
		Assert.assertFalse(client.isConnected());
	}
	
}
