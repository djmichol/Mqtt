package com.michal.mqtt.api.dictionary.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Dictionary response model with values")
public class DictionaryResponse implements Serializable{

    @ApiModelProperty(value = "Dictionary name")
    private String dictionaryName;
    @ApiModelProperty(value = "Dictionary code")
    private String dictionaryCode;
    @ApiModelProperty(value = "Dictionary values")
    private List<String> dictionaryValues;

    public DictionaryResponse() {
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public List<String> getDictionaryValues() {
        return dictionaryValues;
    }

    public void setDictionaryValues(List<String> dictionaryValues) {
        this.dictionaryValues = dictionaryValues;
    }
}
