package com.michal.dao;

import com.michal.dao.model.Notification;

import java.util.List;

public interface NotificationDao {

    Notification create(Notification data);
    void readNotification(Long notificationId);
    List<Notification> getAllNewNotifications();
    List<Notification> getAllNotifications(String topic);

}
