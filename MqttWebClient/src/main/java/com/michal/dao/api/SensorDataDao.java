package com.michal.dao.api;

import java.util.List;

import com.michal.dao.model.mqttdata.SensorData;

public interface SensorDataDao {

    SensorData create(SensorData data);

    List<SensorData> getAllData();

    List<SensorData> getDataByType(String type);

}
