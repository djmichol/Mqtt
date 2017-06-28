package com.michal.mqtt.callback;

import com.michal.mqtt.MqttClientImpl;

public class CallbackFactory {

    public static MqttCallbackAbstract createCallback(CallbackEnum callback, MqttClientImpl client) {
        switch (callback) {
            case PRINT_CALLBACK:
                return new PrintCallback(client);
            case SENSOR_DATA_CALLBACK:
                return new SensorDataCallback(client);
            case NOTIFICATION_CALLBACK:
                return new NotificationsCallback(client);
            default:
                return new PrintCallback(client);
        }
    }
}
