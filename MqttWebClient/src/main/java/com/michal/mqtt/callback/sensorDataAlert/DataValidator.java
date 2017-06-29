package com.michal.mqtt.callback.sensorDataAlert;


import com.michal.dao.NotificationDao;
import com.michal.dao.model.Notification;
import com.michal.mqtt.MqttClientImpl;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Date;

public abstract class DataValidator {

    private NotificationDao notificationDao;
    private MqttClientImpl mqttClient;

    public DataValidator(NotificationDao notificationDao, MqttClientImpl mqttClient){
        this.notificationDao = notificationDao;
        this.mqttClient = mqttClient;
    }

    public abstract void validate(String topic, String value);

    public void sendMessage(String topic, String message) throws MqttException {
        if(mqttClient!=null) {
            mqttClient.publish(topic, message, 0);
        }
    }

    public void addNotification(String topic, String message){
        Notification notification = new Notification();
        notification.setRead(false);
        notification.setDataTimestamp(new Date());
        notification.setTopic(topic);
        notification.setMessage(message);
        notificationDao.create(notification);
    }

}
