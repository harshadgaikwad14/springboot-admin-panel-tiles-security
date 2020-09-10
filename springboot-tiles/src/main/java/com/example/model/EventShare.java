package com.example.model;

import java.io.Serializable;

public class EventShare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long eventShareId;
	private long eventId;
	private long clientId;
	private String drName;
	private String eventName;
	private String eventDate;
	private String eventShareDate;
	private long eventShareCount;
	public long getEventShareId() {
		return eventShareId;
	}
	public void setEventShareId(long eventShareId) {
		this.eventShareId = eventShareId;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventShareDate() {
		return eventShareDate;
	}
	public void setEventShareDate(String eventShareDate) {
		this.eventShareDate = eventShareDate;
	}
	public long getEventShareCount() {
		return eventShareCount;
	}
	public void setEventShareCount(long eventShareCount) {
		this.eventShareCount = eventShareCount;
	}
	@Override
	public String toString() {
		return "EventShare [eventShareId=" + eventShareId + ", eventId=" + eventId + ", clientId=" + clientId
				+ ", drName=" + drName + ", eventName=" + eventName + ", eventDate=" + eventDate + ", eventShareDate="
				+ eventShareDate + ", eventShareCount=" + eventShareCount + "]";
	}
	
	
	

}
