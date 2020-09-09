package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ClientRegistration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String drName;
	private long speciality;
	private String mobileNo;
	private String emailId;
	private String city;
	private String state;
	private LocalDateTime modifiedAt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	
	
	
	public long getSpeciality() {
		return speciality;
	}
	public void setSpeciality(long speciality) {
		this.speciality = speciality;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	@Override
	public String toString() {
		return "ClientRegistration [id=" + id + ", drName=" + drName + ", speciality=" + speciality + ", mobileNo="
				+ mobileNo + ", emailId=" + emailId + ", city=" + city + ", state=" + state + ", modifiedAt="
				+ modifiedAt + "]";
	}

	

}
