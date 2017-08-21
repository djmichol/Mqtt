package com.michal.mqtt.api.converter;

public abstract class ResponseConverter<IN,OUT> extends Converter<IN, OUT> {

    protected abstract void prepareLinks(IN in, OUT out);
}
