package com.michal.mqtt.engine.notifications;

import com.michal.dao.model.rule.Action;

public interface NotificationAction {

    void sendNotification(Action action, String mqttData, String topic);

}
