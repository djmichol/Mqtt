package com.michal.dao.model.rule;

import com.michal.dao.model.networkstructure.Sensor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

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
    @Column(name = "groovy_rule_message", nullable = false)
    private String message;
    @Enumerated(EnumType.STRING)
    @Column(name = "groovy_rule_type", nullable = false)
    private VariableType type;
    @Column(name = "groovy_rule_action", nullable = false)
    @Enumerated
    @ElementCollection(targetClass = ActionType.class, fetch = FetchType.EAGER)
    private List<ActionType> actions;
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

    public List<ActionType> getActions() {
        return actions;
    }

    public void setActions(List<ActionType> actions) {
        this.actions = actions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum VariableType {
        NUMBER, BOOLEAN, STRING;
    }

    public enum ActionType {
        MAIL, SMS;
    }
}