package com.example.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.Event;
import com.example.model.User;
import com.example.service.SpecialityCaptchaService;

@Component
public class EventValidator implements Validator {

	Logger logger = LoggerFactory.getLogger(EventValidator.class);

	

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Event event = (Event) o;

		logger.info("validate - specialityCaptcha : {}", event);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventName", "NotEmpty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "eventDate", "NotEmpty");

		if (event.getEventName().length() < 3 || event.getEventName().length() > 32) {
			errors.rejectValue("eventName", "Size.createEventForm.eventName");
		}

		

		List<MultipartFile> files = event.getImages();
		
		if (files.isEmpty()) {
			errors.rejectValue("images", "NotEmpty.createEventForm.images");
		}

	

	}
}
