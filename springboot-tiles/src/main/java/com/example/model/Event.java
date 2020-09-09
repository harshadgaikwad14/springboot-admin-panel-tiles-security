package com.example.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class Event implements Serializable {
	private static final long serialVersionUID = 74458L;
	
	private long id;

	private String eventName;
	
	private String eventDate;

	private List<MultipartFile> images;
	
	

	
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<MultipartFile> getImages() {
		return images;
	}

	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + ", eventDate=" + eventDate + "]";
	}

	

	
	
	

}