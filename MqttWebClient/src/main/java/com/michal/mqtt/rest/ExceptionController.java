package com.michal.mqtt.rest;

import com.michal.mqtt.rest.model.ErrorData;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ErrorData handleNotValidException(MethodArgumentNotValidException e) {
        //TODO add exception builder
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(value = MqttException.class)
    public ErrorData handleNotValidException(MqttException e) {
        ErrorData error = new ErrorData(HttpStatus.BAD_REQUEST.value(), "Mqtt error");
        error.addFieldError(e.getMessage());
        return error;
    }

    @ExceptionHandler(value =Exception.class)
    public ErrorData handleOtherException(Exception e) {
        ErrorData error = new ErrorData(HttpStatus.BAD_REQUEST.value(), "Other error");
        error.addFieldError(e.getMessage());
        return error;
    }

    private ErrorData processFieldErrors(List<FieldError> fieldErrors) {
        ErrorData error = new ErrorData(HttpStatus.BAD_REQUEST.value(), "Validation error");
        for (FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getDefaultMessage());
        }
        return error;
    }

}
