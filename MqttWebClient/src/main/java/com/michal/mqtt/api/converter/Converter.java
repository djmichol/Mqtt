package com.michal.mqtt.api.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<IN,OUT>  implements org.springframework.core.convert.converter.Converter<IN,OUT> {

    public List<OUT> convert(List<IN> inList){
        return inList.stream().map(this::convert).collect(Collectors.toList());
    }

}
