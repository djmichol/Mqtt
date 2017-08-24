package com.michal.dao.model.rule;

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
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "action")
public class Action implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "action_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "action_type", nullable = false)
    private ActionType type;
    @Column(name = "action_addressee" , nullable = false)
    private String addressee;
    @Column(name = "action_message" , nullable = false)
    private String message;
    @Column(name = "action_subject" , nullable = false)
    private String subject;
    @ManyToMany(mappedBy = "actions")
    private Set<GroovyRule> groovyRules;

    public Action() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Set<GroovyRule> getGroovyRules() {
        return groovyRules;
    }

    public void setGroovyRules(Set<GroovyRule> groovyRules) {
        this.groovyRules = groovyRules;
    }

    public enum ActionType {
        MAIL, SMS
    }
}