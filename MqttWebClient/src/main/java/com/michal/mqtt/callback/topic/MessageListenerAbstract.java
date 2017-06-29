package com.michal.mqtt.callback.topic;

import com.michal.mqtt.callback.sensorDataAlert.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;

public abstract class MessageListenerAbstract implements IMqttMessageListener{

    final Logger logger = LogManager.getLogger(MessageListenerAbstract.class);

    protected DataValidator dataValidator;
}
