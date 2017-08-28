package com.michal.dao.api;

import java.util.Date;
import java.util.List;

import com.michal.dao.model.mqttdata.SensorData;

public interface SensorDataDao {

    SensorData create(SensorData data);

    List<SensorData> getAllData();

    List<SensorData> getAllDataFrom(Date from);

    List<SensorData> getDataByType(String type);

}
