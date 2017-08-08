package com.michal.dao.api;

import com.michal.dao.model.homestructure.Room;
import com.michal.dao.model.networkstructure.Sensor;

import java.util.List;

public interface SensorDao {

    Sensor create(Sensor brokerNode);

    List<Sensor> getAllSensors();

    Sensor getByTopic(String topic);

    Sensor get(Long id);

    boolean remove(Long id);

    void addRoomToSensor(Room room, Long id);

}
