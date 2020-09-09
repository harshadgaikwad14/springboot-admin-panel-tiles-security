package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.model.ClientRegistration;
import com.example.model.ValidationError;
import com.example.repository.ClientRegistratationRepository;

@Service
public class ClientRegistrationServiceImpl implements ClientRegistrationService {
	@Autowired
	private ClientRegistratationRepository clientRegistratationRepository;

	@Override
	public long save(ClientRegistration clientRegistration) {
		// TODO Auto-generated method stub
		return clientRegistratationRepository.save(clientRegistration);
	}

	@Override
	public long update(ClientRegistration clientRegistration, long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ClientRegistration> findAll() {
		// TODO Auto-generated method stub
		return clientRegistratationRepository.findByAll();
	}

	@Override
	public ClientRegistration findById(long id) {
		// TODO Auto-generated method stub
		return clientRegistratationRepository.findById(id);
	}

	@Override
	public void deleteById(long id) {
		clientRegistratationRepository.deleteById(id);

	}

	@Override
	public List<ValidationError> validate(ClientRegistration clientRegistration) {

		List<ValidationError> validationErrors = new ArrayList<>();

		if (clientRegistration != null) {

			if (StringUtils.isEmpty(clientRegistration.getDrName())) {
				ValidationError validationError = new ValidationError();
				validationError.setField("drName");
				validationError.setMessage("drName is empty");
				validationError.setRejectedValue(clientRegistration.getDrName());
				validationErrors.add(validationError);
			}
			if (StringUtils.isEmpty(clientRegistration.getMobileNo())) {
				ValidationError validationError = new ValidationError();
				validationError.setField("mobileNo");
				validationError.setMessage("mobileNo is empty");
				validationError.setRejectedValue(clientRegistration.getMobileNo());
				validationErrors.add(validationError);
			}
			if (StringUtils.isEmpty(clientRegistration.getEmailId())) {
				ValidationError validationError = new ValidationError();
				validationError.setField("emailId");
				validationError.setMessage("emailId is empty");
				validationError.setRejectedValue(clientRegistration.getEmailId());
				validationErrors.add(validationError);
			}
			if (StringUtils.isEmpty(clientRegistration.getCity())) {
				ValidationError validationError = new ValidationError();
				validationError.setField("city");
				validationError.setMessage("city is empty");
				validationError.setRejectedValue(clientRegistration.getCity());
				validationErrors.add(validationError);
			}
			if (StringUtils.isEmpty(clientRegistration.getState())) {
				ValidationError validationError = new ValidationError();
				validationError.setField("state");
				validationError.setMessage("state is empty");
				validationError.setRejectedValue(clientRegistration.getState());
				validationErrors.add(validationError);
			}

			if (clientRegistration.getSpeciality() < 1) {

				ValidationError validationError = new ValidationError();
				validationError.setField("speciality");
				validationError.setMessage("speciality is empty");
				validationError.setRejectedValue(clientRegistration.getSpeciality());
				validationErrors.add(validationError);

			}
		} else {
			ValidationError validationError = new ValidationError();
			validationError.setMessage("body is empty");
			validationError.setRejectedValue(clientRegistration);
			validationErrors.add(validationError);

		}
		return validationErrors;
	}

}
