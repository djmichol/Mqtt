package com.michal.dao.model;

import com.michal.mqtt.callback.CallbackEnum;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mqttBrokers", uniqueConstraints = { @UniqueConstraint( columnNames = { "broker_uri", "broker_callback" } ) } )
public class Broker implements Serializable {

    private static final long serialVersionUID = -4177899607190590832L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "broker_id")
    private Long id;

    @Column(name = "broker_uri", nullable = false)
    private String uri;

    @Column(name = "broker_user", nullable = false)
    private String user;

    @Column(name = "broker_password", nullable = false)
    private String password;

    @Column(name = "broker_callback", nullable = false)
    @Enumerated(EnumType.STRING)
    private CallbackEnum callbackEnum;

    @OneToMany(mappedBy = "broker", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Topic> topics = new HashSet<Topic>(0);

    public Broker() {
    }

    public Broker(String uri, String user, String password, CallbackEnum callbackEnum) {
        this.uri = uri;
        this.user = user;
        this.password = password;
        this.callbackEnum = callbackEnum;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public CallbackEnum getCallbackEnum() {
        return callbackEnum;
    }

    public void setCallbackEnum(CallbackEnum callbackEnum) {
        this.callbackEnum = callbackEnum;
    }
}
