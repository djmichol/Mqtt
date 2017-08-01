package com.michal.mqtt.api.utils;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

public class RestCallService {

    private Environment env;

    public RestCallService(Environment env) {
        this.env = env;
    }

    public ResponseEntity<Serializable> postRestAuthByUrl(String url, Object body, Class responseClass) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(env.getProperty("auth.url") + url, body, responseClass);
    }
}
