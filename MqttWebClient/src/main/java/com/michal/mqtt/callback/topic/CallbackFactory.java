package com.michal.mqtt.callback.topic;

public class CallbackFactory {

    public CallbackFactory(PrintMessageCallback printMessageCallback, SensorDataMessageCallback sensorDataMessageCallback, NotificationsMessageCallback notificationsMessageCallback){
        this.printMessageCallback = printMessageCallback;
        this.sensorDataMessageCallback = sensorDataMessageCallback;
        this.notificationsMessageCallback = notificationsMessageCallback;
    }

    private PrintMessageCallback printMessageCallback;
    private SensorDataMessageCallback sensorDataMessageCallback;
    private NotificationsMessageCallback notificationsMessageCallback;

    public MessageListenerAbstract createCallback(CallbackEnum callback) {
        switch (callback) {
            case PRINT_CALLBACK:
                return printMessageCallback;
            case SENSOR_DATA_CALLBACK:
                return sensorDataMessageCallback;
            case NOTIFICATION_CALLBACK:
                return notificationsMessageCallback;
            default:
                return printMessageCallback;
        }
    }
}
