package com.michal.mqtt.callback.topic;

import com.michal.dao.NotificationDao;
import com.michal.dao.model.Notification;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;


public class NotificationsMessageCallback implements IMqttMessageListener {

    private NotificationDao notificationDao;

    public NotificationsMessageCallback(NotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Notification notification = new Notification();
        notification.setMessage(new String(message.getPayload()));
        notification.setTopic(topic);
        notification.setDataTimestamp(new Date(System.currentTimeMillis()));
        notification.setRead(false);
        notificationDao.create(notification);
    }
}
