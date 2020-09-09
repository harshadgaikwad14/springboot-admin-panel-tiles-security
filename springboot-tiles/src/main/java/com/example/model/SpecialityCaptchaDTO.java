package com.example.model;

import java.io.Serializable;

public class SpecialityCaptchaDTO implements Serializable {
	private static final long serialVersionUID = 74458L;

	private long id;

	private String captchaText;

	private String captchaImagePath;

	private long speciality;
	
	private String specialityName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCaptchaText() {
		return captchaText;
	}

	public void setCaptchaText(String captchaText) {
		this.captchaText = captchaText;
	}

	public long getSpeciality() {
		return speciality;
	}

	public void setSpeciality(long speciality) {
		this.speciality = speciality;
	}

	public String getCaptchaImagePath() {
		return captchaImagePath;
	}

	public void setCaptchaImagePath(String captchaImagePath) {
		this.captchaImagePath = captchaImagePath;
	}
	

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

	@Override
	public String toString() {
		return "SpecialityCaptchaDTO [id=" + id + ", captchaText=" + captchaText + ", captchaImagePath="
				+ captchaImagePath + ", speciality=" + speciality + ", specialityName=" + specialityName + "]";
	}

	
	

}