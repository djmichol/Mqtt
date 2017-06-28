package com.michal.mqtt.callback;

import com.michal.config.MqttApplicationConfiguration;
import com.michal.dao.NotificationDao;
import com.michal.dao.model.Notification;
import com.michal.mqtt.MqttClientImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;


public class NotificationsCallback extends MqttCallbackAbstract {

    private NotificationDao notificationDao;

    public NotificationsCallback(MqttClientImpl client) {
        super(client);
        notificationDao = (NotificationDao) MqttApplicationConfiguration.getBean(NotificationDao.class);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        Notification notification = new Notification();
        notification.setMessage(new String(message.getPayload()));
        notification.setTopic(topic);
        notification.setDataTimestamp(new Date(System.currentTimeMillis()));
        notificationDao.create(notification);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("MessageRequestModel published succesfull!!!");
    }
}
