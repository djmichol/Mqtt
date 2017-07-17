package com.michal.dao;

import java.util.List;

import com.michal.dao.model.SensorData;

public interface SensorDataDao {

	SensorData create(SensorData data);
	List<SensorData> getAllData();	
	List<SensorData> getDataByType(String type);
	List<SensorData> getDataForRoomByType(String room, String type);
	List<SensorData> getLatestData();
	
}
