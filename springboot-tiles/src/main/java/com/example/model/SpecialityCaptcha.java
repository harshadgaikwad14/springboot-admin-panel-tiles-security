package com.example.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class SpecialityCaptcha implements Serializable {
	private static final long serialVersionUID = 74458L;

	private long id;

	private String captchaText;
	
	
	private long speciality;

	private MultipartFile image;
	
	private String captchaImagePath;

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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	

	public String getCaptchaImagePath() {
		return captchaImagePath;
	}

	public void setCaptchaImagePath(String captchaImagePath) {
		this.captchaImagePath = captchaImagePath;
	}

	@Override
	public String toString() {
		return "SpecialityCaptcha [id=" + id + ", captchaText=" + captchaText + ", speciality=" + speciality
				+ ", image=" + image + "]";
	}
	
	


	

	

}