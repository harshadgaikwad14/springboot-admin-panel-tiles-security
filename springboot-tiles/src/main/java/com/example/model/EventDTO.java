package com.example.model;

import java.io.Serializable;
import java.util.List;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 74458L;

	private long id;

	private String name;

	private String eventDate;

	private List<EventImageDTO> images;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "EventDTO [id=" + id + ", name=" + name + ", eventDate=" + eventDate + ", images=" + images + "]";
	}

	public void setName(String name) {
		this.name = name;
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

	public List<EventImageDTO> getImages() {
		return images;
	}

	public void setImages(List<EventImageDTO> images) {
		this.images = images;
	}
	
	

}