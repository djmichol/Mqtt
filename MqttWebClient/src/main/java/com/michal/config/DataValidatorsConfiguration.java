package com.michal.config;

import com.michal.dao.NotificationDao;
import com.michal.mqtt.callback.sensorDataAlert.DataValidatorFactory;
import com.michal.mqtt.callback.sensorDataAlert.HumidityValidator;
import com.michal.mqtt.callback.sensorDataAlert.TemperatureValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataValidatorsConfiguration {

    @Bean
    public TemperatureValidator temperatureValidator(NotificationDao notificationDao){
        return new TemperatureValidator(notificationDao);
    }

    @Bean
    public HumidityValidator humidityValidator(NotificationDao notificationDao){
        return new HumidityValidator(notificationDao);
    }

    @Bean
    public DataValidatorFactory dataValidatorFactory(TemperatureValidator temperatureValidator, HumidityValidator humidityValidator){
        return new DataValidatorFactory(temperatureValidator, humidityValidator);
    }

}
