package com.michal.mqtt.engine.client;

import com.michal.dao.api.SensorDataDao;
import com.michal.dao.model.mqttdata.SensorData;
import com.michal.dao.model.networkstructure.Sensor;

import java.util.Date;

public class SensorDataListener implements SensorMessageListener {

    private SensorDataDao sensorDataDao;

    public SensorDataListener(SensorDataDao sensorDataDao) {
        this.sensorDataDao = sensorDataDao;
    }

    @Override
    public void mesageRecived(Sensor sensor, String topic, String data) {
        SensorData sensorData = getSensorData(sensor, data);
        sensorDataDao.create(sensorData);
    }

    private SensorData getSensorData(Sensor sensor, String data) {
        SensorData sensorData = new SensorData();
        sensorData.setType(sensor.getType());
        sensorData.setData(data);
        sensorData.setSensor(sensor);
        sensorData.setTimestamp(new Date());
        return sensorData;
    }
}
