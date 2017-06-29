package com.michal.mqtt.callback.sensorDataAlert;

import com.michal.dao.NotificationDao;

import java.math.BigDecimal;

public class TemperatureValidator extends DataValidator {

    public TemperatureValidator(NotificationDao notificationDao) {
        super(notificationDao);
    }

    @Override
    public void validate(String topic, String value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        if (bigDecimal.compareTo(new BigDecimal(23)) == 1) {
            addNotification(topic, "Temperature too high: " + value + "!!!");
        } else if (bigDecimal.compareTo(new BigDecimal(17)) == 0) {
            addNotification(topic, "Temperature too low: " + value + "!!!");
        }
    }
}
