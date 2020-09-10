package com.example.model;

import java.io.Serializable;

public class ClientEventShare implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long eventId;
	private long clientId;

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

}
