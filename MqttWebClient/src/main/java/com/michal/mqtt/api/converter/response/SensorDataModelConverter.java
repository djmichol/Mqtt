package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.mqttdata.SensorData;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.mqtt.model.response.SensorDataResponseModel;

public class SensorDataModelConverter extends Converter<SensorData,SensorDataResponseModel> {

    @Override
    public SensorDataResponseModel convert(SensorData sensorData) {
        SensorDataResponseModel sensorDataModel = new SensorDataResponseModel();
        sensorDataModel.setDataTimestamp(sensorData.getTimestamp());
        sensorDataModel.setDataType(sensorData.getType());
        sensorDataModel.setSensorData(sensorData.getData());
        sensorDataModel.setSensorId(sensorData.getSensor().getId());
        return sensorDataModel;
    }
}
