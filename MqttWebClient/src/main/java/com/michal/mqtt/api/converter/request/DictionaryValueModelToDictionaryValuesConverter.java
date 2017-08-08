package com.michal.mqtt.api.converter.request;

import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.model.dictionary.DictionaryValues;
import com.michal.mqtt.api.converter.Converter;
import com.michal.mqtt.api.dictionary.model.request.DictionaryValueRequestModel;

public class DictionaryValueModelToDictionaryValuesConverter extends Converter<DictionaryValueRequestModel, DictionaryValues> {

    private DictionaryDefinitionDao dictionaryDefinitionDao;

    public DictionaryValueModelToDictionaryValuesConverter(DictionaryDefinitionDao dictionaryDefinitionDao) {
        this.dictionaryDefinitionDao = dictionaryDefinitionDao;
    }

    @Override
    public DictionaryValues convert(DictionaryValueRequestModel dictionaryValueRequestModel) {
        DictionaryValues dictionaryValues = new DictionaryValues();
        if(dictionaryValueRequestModel!=null){
            dictionaryValues.setDictValVal(dictionaryValueRequestModel.getValue());
            dictionaryValues.setDictionaryDefinition(dictionaryDefinitionDao.getDictionaryDefinitionByCode(dictionaryValueRequestModel.getDictionaryCode()));
        }
        return dictionaryValues;
    }
}
