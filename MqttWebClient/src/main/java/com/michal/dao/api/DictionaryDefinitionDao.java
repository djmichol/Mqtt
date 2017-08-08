package com.michal.dao.api;

import com.michal.dao.model.dictionary.DictionaryDefinition;

import java.util.List;

public interface DictionaryDefinitionDao {

    DictionaryDefinition addDictoinaryDefinition(DictionaryDefinition dictionaryDefinition);
    DictionaryDefinition getDictionaryDefinitionByCode(String code);
    List<DictionaryDefinition> getAllDefinitions();

}
