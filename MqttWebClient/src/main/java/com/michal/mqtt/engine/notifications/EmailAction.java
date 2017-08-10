package com.michal.mqtt.engine.notifications;

public class EmailAction implements NotificationAction{
    @Override
    public void sendNotification(String message) {
        System.out.println("mail");
    }
}
