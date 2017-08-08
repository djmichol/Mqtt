package com.michal.mqtt.api.dictionary;

import com.michal.dao.api.DictionaryDefinitionDao;
import com.michal.dao.api.DictionaryValuesDao;
import com.michal.dao.model.dictionary.DictionaryDefinition;
import com.michal.dao.model.dictionary.DictionaryValues;
import com.michal.mqtt.api.converter.request.DictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
import com.michal.mqtt.api.converter.request.DictionaryValueModelToDictionaryValuesConverter;
import com.michal.mqtt.api.converter.response.DictionaryToDictionaryResponseConverter;
import com.michal.mqtt.api.dictionary.model.request.DictionaryDefinitionRequest;
import com.michal.mqtt.api.dictionary.model.request.DictionaryValueRequestModel;
import com.michal.mqtt.api.dictionary.model.response.DictionaryResponse;
import com.michal.mqtt.api.utils.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryApi {

    private DictionaryValuesDao dictionaryValuesDao;
    private DictionaryDefinitionDao dictionaryDefinitionDao;
    private DictionaryToDictionaryResponseConverter dictionaryToDictionaryResponseConverter;
    private DictionaryDefinitionRequestModelToDictionaryDefinitionConverter dictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
    private DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter;

    public DictionaryApi(DictionaryValuesDao dictionaryValuesDao, DictionaryDefinitionDao dictionaryDefinitionDao, DictionaryToDictionaryResponseConverter dictionaryToDictionaryResponseConverter, DictionaryDefinitionRequestModelToDictionaryDefinitionConverter
            dictionaryDefinitionRequestModelToDictionaryDefinitionConverter, DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter) {
        this.dictionaryDefinitionDao = dictionaryDefinitionDao;
        this.dictionaryValuesDao = dictionaryValuesDao;
        this.dictionaryToDictionaryResponseConverter = dictionaryToDictionaryResponseConverter;
        this.dictionaryDefinitionRequestModelToDictionaryDefinitionConverter = dictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
        this.dictionaryValueModelToDictionaryValuesConverter = dictionaryValueModelToDictionaryValuesConverter;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{code}")
    public ResponseEntity<DictionaryResponse> getDictionaryByCode(@PathVariable("code") String code) {
        DictionaryResponse response = dictionaryToDictionaryResponseConverter.convert(dictionaryDefinitionDao.getDictionaryDefinitionByCode(code));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DictionaryResponse>> getAllDictionaries() {
        List<DictionaryResponse> response = dictionaryToDictionaryResponseConverter.convert(dictionaryDefinitionDao.getAllDefinitions());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/definition")
    public ResponseEntity<DictionaryResponse> addDictionaryDefinition(@Valid @RequestBody DictionaryDefinitionRequest dictionaryDefinitionRequest) {
        DictionaryDefinition dictionaryDefinition = dictionaryDefinitionDao.addDictoinaryDefinition(dictionaryDefinitionRequestModelToDictionaryDefinitionConverter.convert
                (dictionaryDefinitionRequest));
        return new ResponseEntity<>(dictionaryToDictionaryResponseConverter.convert(dictionaryDefinition), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/value")
    public ResponseEntity<SimpleResponse> addDictionaryValue(@Valid @RequestBody DictionaryValueRequestModel dictionaryValueRequestModel){
        DictionaryValues dictionaryValue = dictionaryValueModelToDictionaryValuesConverter.convert(dictionaryValueRequestModel);
        dictionaryValuesDao.addDictionaryValue(dictionaryValue);
        return new ResponseEntity<>(SimpleResponse.create(dictionaryValue.getDictValVal(),"Dictionary value added"), HttpStatus.OK);
    }
}
