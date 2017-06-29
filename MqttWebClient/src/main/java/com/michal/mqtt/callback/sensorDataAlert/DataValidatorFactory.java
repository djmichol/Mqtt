package com.michal.mqtt.callback.sensorDataAlert;

public class DataValidatorFactory {

    private TemperatureValidator temperatureValidator;
    private HumidityValidator humidityValidator;

    public DataValidatorFactory(TemperatureValidator temperatureValidator, HumidityValidator humidityValidator) {
        this.temperatureValidator = temperatureValidator;
        this.humidityValidator = humidityValidator;
    }

    public DataValidator createCallback(String topic) {
        switch (topic) {
            case "temperature":
                return temperatureValidator;
            case "humidity":
                return humidityValidator;
            default:
                return temperatureValidator;
        }
    }
}
