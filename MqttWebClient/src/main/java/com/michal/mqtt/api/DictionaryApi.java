package com.michal.mqtt.api;

import com.michal.dao.DictionaryDefinitionDao;
import com.michal.dao.DictionaryValuesDao;
import com.michal.dao.model.DictionaryDefinition;
import com.michal.dao.model.DictionaryValues;
import com.michal.mqtt.api.converter.request.DictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
import com.michal.mqtt.api.converter.request.DictionaryValueModelToDictionaryValuesConverter;
import com.michal.mqtt.api.converter.response.DictionaryToDictionaryResponseModelConverter;
import com.michal.mqtt.api.model.request.DictionaryDefinitionRequest;
import com.michal.mqtt.api.model.request.DictionaryValueRequestModel;
import com.michal.mqtt.api.model.response.DictionaryResponse;
import com.michal.mqtt.api.model.response.SimpleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryApi {

    private DictionaryValuesDao dictionaryValuesDao;
    private DictionaryDefinitionDao dictionaryDefinitionDao;
    private DictionaryToDictionaryResponseModelConverter dictionaryToDictionaryResponseModelConverter;
    private DictionaryDefinitionRequestModelToDictionaryDefinitionConverter dictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
    private DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter;

    public DictionaryApi(DictionaryValuesDao dictionaryValuesDao, DictionaryDefinitionDao dictionaryDefinitionDao, DictionaryToDictionaryResponseModelConverter
            dictionaryToDictionaryResponseModelConverter, DictionaryDefinitionRequestModelToDictionaryDefinitionConverter
            dictionaryDefinitionRequestModelToDictionaryDefinitionConverter, DictionaryValueModelToDictionaryValuesConverter dictionaryValueModelToDictionaryValuesConverter) {
        this.dictionaryDefinitionDao = dictionaryDefinitionDao;
        this.dictionaryValuesDao = dictionaryValuesDao;
        this.dictionaryToDictionaryResponseModelConverter = dictionaryToDictionaryResponseModelConverter;
        this.dictionaryDefinitionRequestModelToDictionaryDefinitionConverter = dictionaryDefinitionRequestModelToDictionaryDefinitionConverter;
        this.dictionaryValueModelToDictionaryValuesConverter = dictionaryValueModelToDictionaryValuesConverter;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{code}")
    public ResponseEntity<DictionaryResponse> getDictionaryByCode(@PathVariable("code") String code) {
        DictionaryResponse response = dictionaryToDictionaryResponseModelConverter.convert(dictionaryDefinitionDao.getDictionaryDefinitionByCode(code));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DictionaryResponse>> getAllDictionaries() {
        List<DictionaryResponse> response = dictionaryToDictionaryResponseModelConverter.convert(dictionaryDefinitionDao.getAllDefinitions());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/definition")
    public ResponseEntity<DictionaryResponse> addDictionaryDefinition(@Valid @RequestBody DictionaryDefinitionRequest dictionaryDefinitionRequest) {
        DictionaryDefinition dictionaryDefinition = dictionaryDefinitionDao.addDictoinaryDefinition(dictionaryDefinitionRequestModelToDictionaryDefinitionConverter.convert
                (dictionaryDefinitionRequest));
        return new ResponseEntity<>(dictionaryToDictionaryResponseModelConverter.convert(dictionaryDefinition), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/value")
    public ResponseEntity<SimpleResponse> addDictionaryValue(@Valid @RequestBody DictionaryValueRequestModel dictionaryValueRequestModel){
        DictionaryValues dictionaryValue = dictionaryValueModelToDictionaryValuesConverter.convert(dictionaryValueRequestModel);
        dictionaryValuesDao.addDictionaryValue(dictionaryValue);
        return new ResponseEntity<>(SimpleResponse.create(dictionaryValue.getDictValVal(),"Dictionary value added"), HttpStatus.OK);
    }
}
