package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.SensorData;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.model.response.SensorDataResponseModel;

public class SensorDataModelConverter extends Converter<SensorData,SensorDataResponseModel> {

    @Override
    public SensorDataResponseModel convert(SensorData sensorData) {
        SensorDataResponseModel sensorDataModel = new SensorDataResponseModel();
        sensorDataModel.setDataPlace(sensorData.getDataPlace());
        sensorDataModel.setDataRoom(sensorData.getDataRoom());
        sensorDataModel.setDataTimestamp(sensorData.getDataTimestamp());
        sensorDataModel.setDataType(sensorData.getDataType());
        sensorDataModel.setSensorData(sensorData.getSensorData());
        return sensorDataModel;
    }
}
