package com.michal.config;

import com.michal.mqtt.rest.converter.SensorDataModelConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfiguration {

    @Bean
    public SensorDataModelConverter sensorDataModelConverter(){
        return new SensorDataModelConverter();
    }
}
