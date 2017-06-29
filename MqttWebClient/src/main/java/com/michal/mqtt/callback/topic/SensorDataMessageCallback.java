package com.michal.mqtt.callback.topic;

import com.michal.dao.SensorDataDao;
import com.michal.dao.model.SensorData;
import com.michal.mqtt.callback.sensorDataAlert.DataValidatorFactory;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Date;

public class SensorDataMessageCallback implements IMqttMessageListener {

    private SensorDataDao dataRepo;
    private DataValidatorFactory dataValidatorFactory;

    public SensorDataMessageCallback(SensorDataDao dataRepo, DataValidatorFactory dataValidatorFactory) {
        this.dataRepo = dataRepo;
        this.dataValidatorFactory = dataValidatorFactory;
    }

    /**
     * message in format home/kitchen/temperature or outdoor//tepmerature
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String messagePayload = new String(message.getPayload());
        SensorData data = prepareData(messagePayload, topic);
        dataRepo.create(data);
        dataValidatorFactory.createCallback(topic.split("/")[TOPIC]).validate(topic,messagePayload);
    }

    private static final int PLACE=0;
    private static final int ROOM=1;
    private static final int TOPIC=2;
    private SensorData prepareData(String messagePayload, String topic) {
        String[] topicElements = topic.split("/");
        SensorData data = new SensorData();
        data.setDataPlace(topicElements[PLACE]);
        data.setDataRoom(topicElements[ROOM]);
        data.setDataType(topicElements[TOPIC]);
        data.setTopic(topic);
        data.setDataTimestamp(new Date(System.currentTimeMillis()));
        data.setSensorData(messagePayload);
        return data;
    }
}
