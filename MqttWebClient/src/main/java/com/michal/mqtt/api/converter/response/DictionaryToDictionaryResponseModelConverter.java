package com.michal.mqtt.api.converter.response;

import com.michal.dao.model.DictionaryDefinition;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.model.response.DictionaryResponse;

import java.util.stream.Collectors;

public class DictionaryToDictionaryResponseModelConverter extends Converter<DictionaryDefinition, DictionaryResponse> {
    @Override
    public DictionaryResponse convert(DictionaryDefinition dictionaryDefinition) {
        DictionaryResponse dictionaryResponse = new DictionaryResponse();
        if(dictionaryDefinition!=null){
            dictionaryResponse.setDictionaryCode(dictionaryDefinition.getDictDefCode());
            dictionaryResponse.setDictionaryName(dictionaryDefinition.getDictDefName());
            dictionaryResponse.setDictionaryValues(dictionaryDefinition.getValues().stream().map(value -> value.getDictValVal()).collect(Collectors.toList()));
        }
        return dictionaryResponse;
    }
}
