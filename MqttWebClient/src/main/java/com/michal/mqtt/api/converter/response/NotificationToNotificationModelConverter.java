package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.Notification;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.model.response.NotificationResponseModel;

public class NotificationToNotificationModelConverter extends Converter<Notification, NotificationResponseModel> {
    @Override
    public NotificationResponseModel convert(Notification notification) {
        NotificationResponseModel notificationResponseModel = new NotificationResponseModel();
        if (notification != null) {
            notificationResponseModel.setDataTimestamp(notification.getDataTimestamp());
            notificationResponseModel.setMessage(notification.getMessage());
            notificationResponseModel.setTopic(notification.getTopic());
        }
        return notificationResponseModel;
    }
}
