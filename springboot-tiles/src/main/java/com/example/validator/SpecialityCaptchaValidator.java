package com.example.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.SpecialityCaptcha;
import com.example.model.SpecialityCaptchaDTO;
import com.example.model.User;
import com.example.service.SpecialityCaptchaService;

@Component
public class SpecialityCaptchaValidator implements Validator {

	Logger logger = LoggerFactory.getLogger(SpecialityCaptchaValidator.class);

	@Autowired
	private SpecialityCaptchaService specialityCaptchaService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		SpecialityCaptcha specialityCaptcha = (SpecialityCaptcha) o;

		logger.info("validate - specialityCaptcha : {}", specialityCaptcha);

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "captchaText", "NotEmpty");

		if (specialityCaptcha.getCaptchaText().length() < 3 || specialityCaptcha.getCaptchaText().length() > 32) {
			errors.rejectValue("captchaText", "Size.createSpecialityCaptchaForm.captchaText");
		}

		if (specialityCaptcha.getSpeciality() < 1) {
			errors.rejectValue("speciality", "SelectDefault.createSpecialityCaptchaForm.speciality");
		}

		MultipartFile file = specialityCaptcha.getImage();
		
		if (file.isEmpty()) {
			errors.rejectValue("image", "NotEmpty.createSpecialityCaptchaForm.image");
		}

		if (!StringUtils.isEmpty(specialityCaptcha.getCaptchaText()) && specialityCaptcha.getSpeciality() > 0) {
			final SpecialityCaptchaDTO specialityCaptchaDTO = specialityCaptchaService.findByCaptchaTxtAndSpecialityId(
					specialityCaptcha.getCaptchaText(), specialityCaptcha.getSpeciality());
			if (specialityCaptchaDTO != null) {
				errors.rejectValue("captchaText", "Duplicate.createSpecialityCaptchaForm.captchaText");
			}

		}

		/*
		 * String specialityName = null;
		 * 
		 * try { final Speciality exSpeciality
		 * =specialityService.findByName(specialityCaptcha.getName());
		 * if(exSpeciality!=null) { specialityName = exSpeciality.getName(); } }catch
		 * (Exception e) { e.getMessage(); }
		 * 
		 * if (specialityName != null) { errors.rejectValue("name",
		 * "Duplicate.createSpecialityForm.name"); }
		 */

	}
}
