package com.michal.config;

import com.michal.dao.api.SensorDataDao;
import com.michal.mqtt.engine.raport.ReportEngine;
import com.michal.mqtt.engine.raport.fileGenerator.ReportGenerator;
import com.michal.mqtt.engine.raport.fileGenerator.impl.ExcelReportGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportConfiguration {

    @Bean
    public ReportEngine reportEngine(SensorDataDao sensorDao){
        return new ReportEngine(sensorDao);
    }

    @Bean
    public ReportGenerator reportGenerator(){
        return new ExcelReportGenerator();
    }
}
