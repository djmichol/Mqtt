package com.michal.mqtt.api.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "Dictionary definitino data")
public class DictionaryDefinitionRequest implements Serializable{

    @NotEmpty(message = "Code can't be empty!")
    @ApiModelProperty(value = "code", allowableValues = "CODE", required = true)
    private String code;
    @NotEmpty(message = "Name can't be empty!")
    @ApiModelProperty(value = "name", allowableValues = "name", required = true)
    private String name;

    public DictionaryDefinitionRequest() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
