package com.michal.mqtt.callback.topic;


public class CallbackFactory {

    public static MessageListenerAbstract createCallback(CallbackEnum callback) {
        switch (callback) {
            case PRINT_CALLBACK:
                return new PrintMessageCallback();
            case SENSOR_DATA_CALLBACK:
                return new SensorDataMessageCallback();
            case NOTIFICATION_CALLBACK:
                return new NotificationsMessageCallback();
            default:
                return new PrintMessageCallback();
        }
    }
}
