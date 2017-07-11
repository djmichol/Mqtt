package com.michal.mqtt.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "Dictionary value data")
public class DictionaryValueRequestModel implements Serializable {

    @NotEmpty(message = "Dictionary code can't be empty!")
    @ApiModelProperty(value = "Dictionary code", allowableValues = "CODE", required = true)
    private String dictionaryCode;
    @NotEmpty(message = "Value can't be empty!")
    @ApiModelProperty(value = "value", allowableValues = "value", required = true)
    private String value;

    public DictionaryValueRequestModel() {
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
