package com.michal.mqtt.engine.client;

import com.michal.dao.model.networkstructure.Sensor;

public interface SensorMessageListener {

    void mesageRecived(Sensor sensor, String topic, String message);
}
