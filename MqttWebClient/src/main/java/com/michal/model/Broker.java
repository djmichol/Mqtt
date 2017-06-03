package com.michal.model;

import java.io.Serializable;
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

@Entity
@Table(name = "mqttBrokers")
public class Broker implements Serializable {

	private static final long serialVersionUID = -4177899607190590832L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "broker_id")
	private Long id;
	
	@Column(name = "broker_uri", nullable = false, unique = true)
	private String uri;
	
	@Column(name = "broker_user", nullable = false)
	private String user;
	
	@Column(name = "broker_password", nullable = false)
	private String password;
	
	@OneToMany(mappedBy = "broker", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Topic> topics = new HashSet<Topic>(0);

	public Broker() {
	}

	public Broker(String uri, String user, String password) {
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
}
