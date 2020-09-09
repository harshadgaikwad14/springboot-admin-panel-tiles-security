package com.example.service;

import java.util.List;

import com.example.model.ClientRegistration;
import com.example.model.ValidationError;

public interface ClientRegistrationService {
	long save(ClientRegistration clientRegistration);
	
	List<ValidationError> validate(ClientRegistration clientRegistration);

	long update(ClientRegistration clientRegistration, final long id);

	List<ClientRegistration> findAll();

	ClientRegistration findById(long id);

	void deleteById(long id);

}
