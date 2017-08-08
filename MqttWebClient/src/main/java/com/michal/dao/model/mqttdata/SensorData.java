package com.michal.dao.model.mqttdata;

import com.michal.dao.model.networkstructure.Sensor;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sensorData")
public class SensorData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "data_id")
    private Long id;
    @Column(name = "data_type", nullable = false)
    private String type;
    @Column(name = "data_data", nullable = false)
    private String data;
    @Column(name = "data_timestamp", nullable = false)
    private Date timestamp;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "data_Sensor", nullable = false)
    private Sensor sensor;

    public SensorData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
