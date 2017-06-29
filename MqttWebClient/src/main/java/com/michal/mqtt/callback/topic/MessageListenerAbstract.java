package com.michal.mqtt.callback.topic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public abstract class MessageListenerAbstract implements IMqttMessageListener{

    final Logger logger = LogManager.getLogger(MessageListenerAbstract.class);
}
