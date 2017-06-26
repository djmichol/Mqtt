package com.michal.mqtt.rest.converter;

import com.michal.dao.model.SensorData;
import com.michal.mqtt.rest.model.SensorDataModel;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;


public class SensorDataModelConverter implements Converter<SensorData, SensorDataModel> {

    @Override
    public SensorDataModel convert(SensorData sensorData) {
        SensorDataModel sensorDataModel = new SensorDataModel();
        sensorDataModel.setDataPlace(sensorData.getDataPlace());
        sensorDataModel.setDataRoom(sensorData.getDataRoom());
        sensorDataModel.setDataTimestamp(sensorData.getDataTimestamp());
        sensorDataModel.setDataType(sensorData.getDataType());
        sensorDataModel.setSensorData(sensorData.getSensorData());
        return sensorDataModel;
    }

    public List<SensorDataModel> convert(List<SensorData> sensorDataList){
        return sensorDataList.stream().map(sensorData -> convert(sensorData)).collect(Collectors.toList());
    }
}
