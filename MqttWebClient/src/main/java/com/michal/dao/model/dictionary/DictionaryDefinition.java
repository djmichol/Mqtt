package com.michal.dao.model.dictionary;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dict_def")
public class DictionaryDefinition implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dic_def_id")
    private Long dictDefId;
    @Column(name = "dic_def_name", nullable = false)
    private String dictDefName;
    @Column(name = "dic_def_code", nullable = false)
    private String dictDefCode;
    @OneToMany(mappedBy = "dictionaryDefinition", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<DictionaryValues> values = new HashSet<>(0);

    public DictionaryDefinition() {
    }

    public Long getDictDefId() {
        return dictDefId;
    }

    public void setDictDefId(Long dictDefId) {
        this.dictDefId = dictDefId;
    }

    public String getDictDefName() {
        return dictDefName;
    }

    public void setDictDefName(String dictDefName) {
        this.dictDefName = dictDefName;
    }

    public String getDictDefCode() {
        return dictDefCode;
    }

    public void setDictDefCode(String dictDefCode) {
        this.dictDefCode = dictDefCode;
    }

    public Set<DictionaryValues> getValues() {
        return values;
    }

    public void setValues(Set<DictionaryValues> values) {
        this.values = values;
    }
}
