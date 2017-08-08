package com.michal.mqtt.api.converter.request;

import com.michal.dao.model.dictionary.DictionaryDefinition;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.dictionary.model.request.DictionaryDefinitionRequest;


public class DictionaryDefinitionRequestModelToDictionaryDefinitionConverter extends Converter<DictionaryDefinitionRequest,DictionaryDefinition> {
    @Override
    public DictionaryDefinition convert(DictionaryDefinitionRequest dictionaryDefinitionRequest) {
        DictionaryDefinition dictionaryDefinition = new DictionaryDefinition();
        if(dictionaryDefinitionRequest!=null){
            dictionaryDefinition.setDictDefCode(dictionaryDefinitionRequest.getCode());
            dictionaryDefinition.setDictDefName(dictionaryDefinitionRequest.getName());
        }
        return dictionaryDefinition;
    }
}
