package com.solstice.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Table(name="PHONE")
public class Phone implements Serializable {

	private static final long serialVersionUID = -7941769011539363185L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="WORK")
	private String work;
	
	@Column(name="HOME")
	private String home;
	
	@Column(name="MOBILE")
	private String mobile;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_id")
	private Contact contact;

	public Phone() {}

	public Phone(String work, String home, String mobile) {
		super();
		this.work = work;
		this.home = home;
		this.mobile = mobile;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
