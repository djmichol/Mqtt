package com.michal.test;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Assert;
import org.junit.Test;

import com.michal.mqtt.MqttClientImpl;


public class MqttClientTest {

	@Test
	public void connectionTest() throws MqttException{
		MqttClientImpl client = new MqttClientImpl("tcp://10.132.221.251:1883", "michal", "home2", "home2");
		client.connect();
		Assert.assertTrue(client.getClient().isConnected());
		client.disconnect();
		Assert.assertFalse(client.getClient().isConnected());
	}
	
}
