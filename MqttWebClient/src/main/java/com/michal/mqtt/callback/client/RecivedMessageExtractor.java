package com.michal.mqtt.callback.client;

import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.SensorDao;
import com.michal.dao.model.dictionary.DictionaryDefinition;
import com.michal.dao.model.dictionary.DictionaryValues;
import com.michal.dao.model.networkstructure.Sensor;

import java.util.stream.Collectors;

public class RecivedMessageExtractor {

    private SensorDao sensorDao;
    private SensorDataListener sensorDataListener;
    private DictionaryDefinitionDao dictionaryDefinitionDao;

    public RecivedMessageExtractor(SensorDao sensorDao, SensorDataListener sensorDataListener, DictionaryDefinitionDao dictionaryDefinitionDao) {
        this.sensorDao = sensorDao;
        this.sensorDataListener = sensorDataListener;
        this.dictionaryDefinitionDao = dictionaryDefinitionDao;
    }

    private String topic;
    private Sensor sensor;

    public RecivedMessageExtractor topic(String topic) {
        this.topic = topic;
        return this;
    }

    public RecivedMessageExtractor extractSensor() {
        Sensor sensor = sensorDao.getByTopic(topic);
        this.sensor = sensor;
        return this;
    }

    private SensorMessageListener checkType() {
        DictionaryDefinition dictionaryDefinition = dictionaryDefinitionDao.getDictionaryDefinitionByCode("SENSOR_TYPES");
        if(dictionaryDefinition.getValues().stream().map(DictionaryValues::getDictValVal).collect(Collectors.toList()).contains(sensor.getType())){
            return  sensorDataListener;
        }
        return  sensorDataListener;
    }

    public void saveMessage(String message){
        SensorMessageListener sensorMessageListener = checkType();
        sensorMessageListener.mesageRecived(this.sensor, this.topic, message);
    }

}
