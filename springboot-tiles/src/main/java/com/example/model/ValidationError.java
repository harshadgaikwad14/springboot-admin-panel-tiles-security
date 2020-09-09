package com.example.model;

import java.io.Serializable;

public class ValidationError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/** The field. */
	private String field;

	/** The message. */
	private String message;

	/** The rejected value. */
	private Object rejectedValue;

	public ValidationError() {
		super();
	}

	public ValidationError( String field, String message, Object rejectedValue) {
		super();
		
		this.field = field;
		this.message = message;
		this.rejectedValue = rejectedValue;
	}
	

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

}
