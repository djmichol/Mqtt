package com.michal.mqtt.api;

import com.michal.dao.NotificationDao;
import com.michal.mqtt.api.converter.response.NotificationToNotificationModelConverter;
import com.michal.mqtt.api.model.response.NotificationResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationsApi {

    private NotificationDao notificationDao;
    private NotificationToNotificationModelConverter notificationToNotificationModelConverter;

    public NotificationsApi(NotificationDao notificationDao, NotificationToNotificationModelConverter notificationToNotificationModelConverter){
        this.notificationDao = notificationDao;
        this.notificationToNotificationModelConverter = notificationToNotificationModelConverter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<NotificationResponseModel>> getAllNotifications() {
        List<NotificationResponseModel> response = notificationToNotificationModelConverter.convert(notificationDao.getAllNewNotifications());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void readNotifications(@RequestParam List<Long> notificationIds){
        notificationIds.forEach(notificationId -> notificationDao.readNotification(notificationId));
    }

}
