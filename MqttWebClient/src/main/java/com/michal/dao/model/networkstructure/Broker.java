package com.michal.dao.model.networkstructure;

import com.michal.dao.model.mqttdata.RecivedMessage;
import com.michal.dao.model.mqttdata.SendMessage;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "broker", uniqueConstraints = {@UniqueConstraint(columnNames = {"broker_url", "broker_name"})})
public class Broker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "broker_id")
    private Long id;

    @Column(name = "broker_url", nullable = false)
    private String url;

    @Column(name = "broker_user", nullable = false)
    private String user;

    @Column(name = "broker_password", nullable = false)
    private String password;

    @Column(name = "broker_name", nullable = false)
    private String name;

    @Column(name = "broker_status")
    private String status;

    @Column(name = "broker_status_since")
    private Date statusSince;

    @OneToMany(mappedBy = "broker", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Node> nodes = new HashSet<>(0);

    @OneToMany(mappedBy = "broker", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<RecivedMessage> recivedMessages = new HashSet<>(0);

    @OneToMany(mappedBy = "broker", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<SendMessage> sendMessages = new HashSet<>(0);

    public Broker() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusSince() {
        return statusSince;
    }

    public void setStatusSince(Date statusSince) {
        this.statusSince = statusSince;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<RecivedMessage> getRecivedMessages() {
        return recivedMessages;
    }

    public void setRecivedMessages(Set<RecivedMessage> recivedMessages) {
        this.recivedMessages = recivedMessages;
    }

    public Set<SendMessage> getSendMessages() {
        return sendMessages;
    }

    public void setSendMessages(Set<SendMessage> sendMessages) {
        this.sendMessages = sendMessages;
    }
}
