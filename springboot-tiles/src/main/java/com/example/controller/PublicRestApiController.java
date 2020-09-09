package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ClientRegistration;
import com.example.model.Speciality;
import com.example.model.StandardResponse;
import com.example.model.ValidationError;
import com.example.service.ClientRegistrationService;
import com.example.service.SpecialityService;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PublicRestApiController {

	Logger logger = LoggerFactory.getLogger(PublicRestApiController.class);

	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private ClientRegistrationService clientRegistrationService;

	

	@GetMapping(value = "/speciality", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> findAll() throws Exception {

		final List<Speciality> list = specialityService.findAll();

		final StandardResponse standardResponse = new StandardResponse();
		standardResponse.setPayload(list);
		standardResponse.setResMsg("SUCCESS");
		return new ResponseEntity<>(standardResponse, HttpStatus.OK);

	}

	@PostMapping(value = "/client/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> createClientRegistration(@RequestBody ClientRegistration clientRegistration)
			 {
		logger.info("clientRegistration : {}", clientRegistration);
		
		final List<ValidationError> validationErrors = clientRegistrationService.validate(clientRegistration);
		
		if(CollectionUtils.isEmpty(validationErrors))
		{
			final long id = clientRegistrationService.save(clientRegistration);

			final StandardResponse standardResponse = new StandardResponse();
			standardResponse.setPayload(id);
			standardResponse.setResMsg("SUCCESS");
			return new ResponseEntity<>(standardResponse, HttpStatus.CREATED);
		}
		

		final StandardResponse standardResponse = new StandardResponse();
		standardResponse.setValErrors(validationErrors);
		standardResponse.setResMsg("FAILED");
		standardResponse.setDevMsg("VALIDATION FAILED");
		return new ResponseEntity<>(standardResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		

	}

	@GetMapping(value = "/client/registration/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> createClientRegistration(@PathVariable long id) throws Exception {

		final ClientRegistration clientRegistration = clientRegistrationService.findById(id);

		final StandardResponse standardResponse = new StandardResponse();
		standardResponse.setPayload(clientRegistration);
		standardResponse.setResMsg("Successs");
		return new ResponseEntity<>(standardResponse, HttpStatus.OK);

	}

}
