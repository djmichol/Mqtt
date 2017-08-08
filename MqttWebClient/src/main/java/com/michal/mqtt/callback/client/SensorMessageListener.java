package com.michal.mqtt.callback.client;

import com.michal.dao.model.networkstructure.Sensor;

public interface SensorMessageListener {

    void mesageRecived(Sensor sensor, String topic, String message);
}
