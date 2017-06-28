package com.michal.dao.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification {

    public Notification() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notification_id")
    private Long notificationId;
    @Column(name = "notification_message", nullable = false)
    private String message;
    @Column(name = "notification_topic", nullable = false)
    private String topic;
    @Column(name = "notification_timestamp", nullable = false)
    private Date dataTimestamp;
    @Column(name = "read", nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean read;

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getDataTimestamp() {
        return dataTimestamp;
    }

    public void setDataTimestamp(Date dataTimestamp) {
        this.dataTimestamp = dataTimestamp;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
