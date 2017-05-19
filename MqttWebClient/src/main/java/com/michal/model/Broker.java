package com.michal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mqttBrokers")
public class Broker implements Serializable{

	private static final long serialVersionUID = -4177899607190590832L;
	@Id
	@Column(name = "mqtt_uri",nullable = false)
	private String uri;
	@Column(name = "mqtt_user",nullable = false)
	private String user;
	@Column(name = "mqtt_password",nullable = false)
	private String password;

	public Broker(){}
	
	public Broker(String uri, String user, String password){
		this.uri = uri;
		this.user = user;
		this.password = password;
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
}
