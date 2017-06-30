package com.michal.mqtt.error;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorData> handleNotValidException(MethodArgumentNotValidException e) {
        ErrorBuilder builder = new ErrorBuilder().message("Validation error");
        e.getBindingResult().getFieldErrors().forEach(fieldError -> builder.error(fieldError.getDefaultMessage()));
        return new ResponseEntity<>(builder.build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MqttException.class)
    public ResponseEntity<ErrorData> handleNotValidException(MqttException e) {
        return new ResponseEntity<>(new ErrorBuilder().message("Mqtt error").error(e.getMessage()).build(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value =Exception.class)
    public ResponseEntity<ErrorData> handleOtherException(Exception e) {
        return new ResponseEntity<>(new ErrorBuilder().message("Other error").error(e.getMessage()).build(),HttpStatus.BAD_REQUEST);
    }
}
