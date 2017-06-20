package com.michal;

import com.michal.config.MqttApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MqttApplicationConfiguration.class})
public class MqttWebClientApp extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MqttWebClientApp.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MqttWebClientApp.class, args);
    }

}