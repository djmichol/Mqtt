package com.michal.mqtt.callback.sensorDataAlert;

import com.michal.dao.NotificationDao;
import com.michal.dao.model.Notification;

import java.util.Date;

public abstract class DataValidator {

    private NotificationDao notificationDao;

    public DataValidator(NotificationDao notificationDao){
        this.notificationDao = notificationDao;
    }

    public abstract void validate(String topic, String value);

    public void addNotification(String topic, String message){
        Notification notification = new Notification();
        notification.setRead(false);
        notification.setDataTimestamp(new Date());
        notification.setTopic(topic);
        notification.setMessage(message);
        notificationDao.create(notification);
    }

}
