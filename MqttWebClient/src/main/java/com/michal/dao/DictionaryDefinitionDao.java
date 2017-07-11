package com.michal.dao;

import com.michal.dao.model.DictionaryDefinition;

import java.util.List;

public interface DictionaryDefinitionDao {

    DictionaryDefinition addDictoinaryDefinition(DictionaryDefinition dictionaryDefinition);
    DictionaryDefinition getDictionaryDefinitionByCode(String code);
    List<DictionaryDefinition> getAllDefinitions();

}
