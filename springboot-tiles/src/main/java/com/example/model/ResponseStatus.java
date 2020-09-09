package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The Class ResponseStatus.
 */
public class ResponseStatus implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5393901375337304365L;

	/** The timestamp. */
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	protected String timestamp;

	/** The res msg. */
	protected String resMsg;

	/** The dev msg. */
	protected String devMsg;

	/**
	 * Instantiates a new response status.
	 */
	public ResponseStatus() {

		this.timestamp = LocalDateTime.now().toString();

	}

	/**
	 * Instantiates a new response status.
	 *
	 * @param resCode the res code
	 * @param resMsg  the res msg
	 * @param devMsg  the dev msg
	 */
	public ResponseStatus(final String resCode, final String resMsg, final String devMsg) {
		super();

		this.resMsg = resMsg;
		// setDevMsg(devMsg); // calling setter method to ensure flag is checked
	}

	/**
	 * Gets the res msg.
	 *
	 * @return the res msg
	 */
	public String getResMsg() {
		return resMsg;
	}

	/**
	 * Gets the dev msg.
	 *
	 * @return the dev msg
	 */
	public String getDevMsg() {
		return devMsg;
	}

	/**
	 * Sets the res msg.
	 *
	 * @param resMsg the new res msg
	 */
	public void setResMsg(final String resMsg) {
		this.resMsg = resMsg;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the dev msg.
	 *
	 * @param devMsg the new dev msg
	 */
	public void setDevMsg(String devMsg) {
		this.devMsg = devMsg;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ResponseStatus [timestamp=" + timestamp + ", resMsg=" + resMsg + ", devMsg=" + devMsg + "]";
	}

}
