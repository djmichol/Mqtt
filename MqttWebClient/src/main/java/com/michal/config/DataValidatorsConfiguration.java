package com.michal.config;

import com.michal.dao.NotificationDao;
import com.michal.mqtt.callback.sensorDataAlert.TemperatureValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataValidatorsConfiguration {

    @Bean
    public TemperatureValidator temperatureValidator(NotificationDao notificationDao){
        return new TemperatureValidator(notificationDao);
    }

}
