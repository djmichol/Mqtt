package com.michal.dao.model.rule;

import com.michal.dao.model.networkstructure.Sensor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groovy_rule")
public class GroovyRule implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groovy_rule_id")
    private Long id;
    @Column(name = "groovy_rule", nullable = false)
    private String rule;
    @Column(name = "groovy_rule_description", nullable = true)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "groovy_rule_type", nullable = false)
    private VariableType type;
    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "groovyRules", nullable = false)
    private Set<Action> actions = new HashSet<>(0);
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groovy_rule_sensor", nullable = false)
    private Sensor sensor;

    public GroovyRule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public VariableType getType() {
        return type;
    }

    public void setType(VariableType type) {
        this.type = type;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public enum VariableType {
        NUMBER, BOOLEAN, STRING;
    }


}