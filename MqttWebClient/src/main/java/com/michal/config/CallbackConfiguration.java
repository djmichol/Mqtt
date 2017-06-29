package com.michal.config;

import com.michal.dao.NotificationDao;
import com.michal.dao.SensorDataDao;
import com.michal.mqtt.callback.sensorDataAlert.TemperatureValidator;
import com.michal.mqtt.callback.topic.CallbackFactory;
import com.michal.mqtt.callback.topic.NotificationsMessageCallback;
import com.michal.mqtt.callback.topic.PrintMessageCallback;
import com.michal.mqtt.callback.topic.SensorDataMessageCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallbackConfiguration {

    @Bean
    public SensorDataMessageCallback sensorDataMessageCallback(SensorDataDao sensorDataDao, TemperatureValidator temperatureValidator) {
        return new SensorDataMessageCallback(sensorDataDao, temperatureValidator);
    }

    @Bean
    public PrintMessageCallback printMessageCallback() {
        return new PrintMessageCallback();
    }

    @Bean
    public NotificationsMessageCallback notificationsMessageCallback(NotificationDao notificationDao) {
        return new NotificationsMessageCallback(notificationDao);
    }

    @Bean
    public CallbackFactory callbackFactory(PrintMessageCallback printMessageCallback, SensorDataMessageCallback sensorDataMessageCallback, NotificationsMessageCallback
            notificationsMessageCallback) {
        return new CallbackFactory(printMessageCallback, sensorDataMessageCallback,notificationsMessageCallback);
    }
}
