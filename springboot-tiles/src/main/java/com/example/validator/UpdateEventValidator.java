package com.example.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.model.Event;
import com.example.model.User;

@Component
public class UpdateEventValidator implements Validator {

	Logger logger = LoggerFactory.getLogger(UpdateEventValidator.class);

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

	}
}
