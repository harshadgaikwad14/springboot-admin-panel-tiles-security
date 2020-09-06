package com.example.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.model.Speciality;
import com.example.model.User;
import com.example.service.SpecialityService;

@Component
public class SpecialityValidator implements Validator {
    @Autowired
    private SpecialityService specialityService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Speciality speciality = (Speciality) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (speciality.getName().length() < 6 || speciality.getName().length() > 32) {
            errors.rejectValue("name", "Size.createSpecialityForm.name");
        }
        
        String specialityName = null;
        
        try
        {
        	final Speciality exSpeciality =specialityService.findByName(speciality.getName());
        	if(exSpeciality!=null)
        	{
        		specialityName = exSpeciality.getName();
        	}
        }catch (Exception e) {
			e.getMessage();
		}
        
        if (specialityName != null) {
            errors.rejectValue("name", "Duplicate.createSpecialityForm.name");
        }

       
    }
}
