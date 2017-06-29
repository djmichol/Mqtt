package com.michal.mqtt.callback.sensorDataAlert;

import com.michal.dao.NotificationDao;

import java.math.BigDecimal;

public class HumidityValidator extends DataValidator{

    public HumidityValidator(NotificationDao notificationDao) {
        super(notificationDao);
    }

    @Override
    public void validate(String topic, String value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        if (bigDecimal.compareTo(new BigDecimal(95)) == 1) {
            addNotification(topic, "Humidity too high: " + value + "!!!");
        } else if (bigDecimal.compareTo(new BigDecimal(60)) == 0) {
            addNotification(topic, "Humidity too low: " + value + "!!!");
        }
    }
}
