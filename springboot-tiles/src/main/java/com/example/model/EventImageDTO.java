package com.example.model;

import java.io.Serializable;

public class EventImageDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String imageUrl;
	private long eventId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	@Override
	public String toString() {
		return "EventImageDTO [id=" + id + ", imageUrl=" + imageUrl + ", eventId=" + eventId + "]";
	}

}
