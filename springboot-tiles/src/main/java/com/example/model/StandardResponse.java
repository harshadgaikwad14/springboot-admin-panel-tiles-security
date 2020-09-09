package com.example.model;

import java.io.Serializable;
import java.util.List;

/**
 * The Class StandardResponse.
 */
public class StandardResponse extends ResponseStatus implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8641012662505823955L;

	/** The val errors. */
	private List<ValidationError> valErrors;

	/** The custom data. */
	private Object payload;

	/**
	 * Instantiates a new standard response.
	 */
	public StandardResponse() {
		// This constructor is intentionally empty. Nothing special is needed
		// here.
	}

	@Override
	public String toString() {
		return "StandardResponse [valErrors=" + valErrors + ", payload=" + payload + "]";
	}

	public List<ValidationError> getValErrors() {
		return valErrors;
	}

	public void setValErrors(List<ValidationError> valErrors) {
		this.valErrors = valErrors;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	
	

}
