package com.michal.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "dict_val")
public class DictionaryValues implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dic_val_id")
    private Long dictValId;
    @Column(name = "dic_val_value", nullable = false)
    private String dictValVal;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dict_val_dict_def", nullable = false)
    private DictionaryDefinition dictionaryDefinition;

    public DictionaryValues() {
    }

    public Long getDictValId() {
        return dictValId;
    }

    public void setDictValId(Long dictValId) {
        this.dictValId = dictValId;
    }

    public String getDictValVal() {
        return dictValVal;
    }

    public void setDictValVal(String dictValVal) {
        this.dictValVal = dictValVal;
    }

    public DictionaryDefinition getDictionaryDefinition() {
        return dictionaryDefinition;
    }

    public void setDictionaryDefinition(DictionaryDefinition dictionaryDefinition) {
        this.dictionaryDefinition = dictionaryDefinition;
    }
}
