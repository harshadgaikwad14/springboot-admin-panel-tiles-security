package com.example.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ClientEventShare;
import com.example.model.ClientRegistration;
import com.example.model.EventDTO;
import com.example.model.EventShare;
import com.example.model.Speciality;
import com.example.model.SpecialityCaptchaDTO;
import com.example.model.StandardResponse;
import com.example.model.ValidationError;
import com.example.service.ClientRegistrationService;
import com.example.service.EventService;
import com.example.service.EventShareService;
import com.example.service.SpecialityCaptchaService;
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

	@Autowired
	private EventService eventService;

	@Autowired
	private SpecialityCaptchaService specialityCaptchaService;
	
	@Autowired
	private EventShareService eventShareService;

	@GetMapping(value = "/speciality", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> findAll() throws Exception {

		final List<Speciality> list = specialityService.findAll();

		final StandardResponse standardResponse = new StandardResponse();
		standardResponse.setPayload(list);
		standardResponse.setResMsg("SUCCESS");
		return new ResponseEntity<>(standardResponse, HttpStatus.OK);

	}

	@GetMapping(value = "/specialityCaptcha", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> getSpecialityCaptcha(@RequestParam(required = false) Long specialityId)
			throws Exception {
		List<SpecialityCaptchaDTO> list = null;
		if (specialityId != null) {

			list = specialityCaptchaService.findAllBySpecialityId(specialityId);
		} else {
			list = specialityCaptchaService.findAll();
		}
		SpecialityCaptchaDTO specialityCaptchaDTO = null;
		if (!CollectionUtils.isEmpty(list))

		{
			logger.info("list :::::: " + list);

			Random rand = new Random();

			specialityCaptchaDTO = list.get(rand.nextInt(list.size()));
		}

		final StandardResponse standardResponse = new StandardResponse();
		if (specialityCaptchaDTO != null) {
			standardResponse.setPayload(specialityCaptchaDTO);
			standardResponse.setResMsg("SUCCESS");
		} else {
			standardResponse.setResMsg("DATA NOT FOUND");
		}

		return new ResponseEntity<>(standardResponse, HttpStatus.OK);

	}

	@PostMapping(value = "/client/registration", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> createClientRegistration(
			@RequestBody ClientRegistration clientRegistration) {
		logger.info("clientRegistration : {}", clientRegistration);

		final List<ValidationError> validationErrors = clientRegistrationService.validate(clientRegistration);

		if (CollectionUtils.isEmpty(validationErrors)) {
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

	@GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> getEvents(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) throws Exception {

		LocalDate currentdate = LocalDate.now();

		if (StringUtils.isEmpty(fromDate) && StringUtils.isEmpty(toDate)) {

			fromDate = currentdate.minusMonths(1).toString();
			toDate = currentdate.plusMonths(3).toString();
		}
		if (!StringUtils.isEmpty(fromDate) && StringUtils.isEmpty(toDate)) {
			final LocalDate fromLocalDate = LocalDate.parse(fromDate);
			fromDate = fromLocalDate.minusMonths(1).toString();
			toDate = fromLocalDate.plusMonths(3).toString();
		}

		if (StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty(toDate)) {
			final LocalDate toLocalDate = LocalDate.parse(toDate);
			fromDate = toLocalDate.minusMonths(4).toString();
			toDate = toLocalDate.toString();
		}

		final List<EventDTO> events = eventService.findAllByDateRange(fromDate, toDate);

		final StandardResponse standardResponse = new StandardResponse();
		standardResponse.setPayload(events);
		standardResponse.setResMsg("Successs");
		return new ResponseEntity<>(standardResponse, HttpStatus.OK);

	}
	
	@PostMapping(value = "/shareEvent", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponse> createShareEvent(
			@RequestBody ClientEventShare clientEventShare) {
		logger.info("clientRegistration : {}", clientEventShare);

		EventShare eventShare = new EventShare();
		//final List<ValidationError> validationErrors = clientRegistrationService.validate(clientRegistration);

		//if (CollectionUtils.isEmpty(validationErrors)) {
		
		eventShare.setClientId(clientEventShare.getClientId());
		eventShare.setEventId(clientEventShare.getEventId());
		eventShare.setEventShareDate(LocalDate.now().toString());
			final long id = eventShareService.save(eventShare);

			final StandardResponse standardResponse = new StandardResponse();
			standardResponse.setPayload(id);
			standardResponse.setResMsg("SUCCESS");
			return new ResponseEntity<>(standardResponse, HttpStatus.CREATED);
		//}

		/*
		 * final StandardResponse standardResponse = new StandardResponse();
		 * standardResponse.setValErrors(validationErrors);
		 * standardResponse.setResMsg("FAILED");
		 * standardResponse.setDevMsg("VALIDATION FAILED"); return new
		 * ResponseEntity<>(standardResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		 */

	}


}
