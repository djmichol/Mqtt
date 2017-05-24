package com.michal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mqttSensorData")
public class SensorData implements Serializable {

	private static final long serialVersionUID = 8921807299383693593L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "data_id")
	private Long dataId;
	@Column(name = "data_place", nullable = false)
	private String dataPlace;
	@Column(name = "data_type", nullable = false)
	private String dataType;
	@Column(name = "data_room", nullable = true)
	private String dataRoom;
	@Column(name = "data", nullable = false)
	private String sensorData;
	@Column(name = "data_timestamp", nullable = false)
	private Date dataTimestamp;

	public SensorData() {
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getDataPlace() {
		return dataPlace;
	}

	public void setDataPlace(String dataPlace) {
		this.dataPlace = dataPlace;
	}

	public String getDataRoom() {
		return dataRoom;
	}

	public void setDataRoom(String dataRoom) {
		this.dataRoom = dataRoom;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getSensorData() {
		return sensorData;
	}

	public void setSensorData(String sensorData) {
		this.sensorData = sensorData;
	}

	public Date getDataTimestamp() {
		return dataTimestamp;
	}

	public void setDataTimestamp(Date dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}

}
