package com.michal.mqtt.error;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorData handleNotValidException(MethodArgumentNotValidException e) {
        ErrorBuilder builder = new ErrorBuilder().status(HttpStatus.BAD_REQUEST.value()).message("Validation error");
        e.getBindingResult().getFieldErrors().forEach(fieldError -> builder.error(fieldError.getDefaultMessage()));
        return builder.build();
    }

    @ExceptionHandler(value = MqttException.class)
    public ErrorData handleNotValidException(MqttException e) {
        return new ErrorBuilder().status(HttpStatus.BAD_REQUEST.value()).message("Mqtt error").error(e.getMessage()).build();
    }

    @ExceptionHandler(value =Exception.class)
    public ErrorData handleOtherException(Exception e) {
        return new ErrorBuilder().status(HttpStatus.BAD_REQUEST.value()).message("Other error").error(e.getMessage()).build();
    }
}
