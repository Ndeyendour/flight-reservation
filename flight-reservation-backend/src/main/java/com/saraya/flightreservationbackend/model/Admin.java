package com.saraya.flightreservationbackend.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity

public class Admin {

	@Id
	private Integer adminId;
	private String password;
	private String adminName;

	public Admin() {
		super();
	}

	public Admin(Integer adminId, String password, String adminName) {
		super();
		this.adminId = adminId;
		this.password = password;
		this.adminName = adminName;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
