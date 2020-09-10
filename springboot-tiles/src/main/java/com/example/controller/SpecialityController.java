package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Speciality;
import com.example.service.SpecialityService;
import com.example.validator.SpecialityValidator;

@Controller
public class SpecialityController {

	Logger logger = LoggerFactory.getLogger(SpecialityController.class);
	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private SpecialityValidator specialityValidator;

	@GetMapping("/createSpeciality")
	public String getSpeciality(Model model) {

		model.addAttribute("createSpecialityForm", new Speciality());

		return "createSpecialityPage";
	}

	@PostMapping("/createSpeciality")
	public ModelAndView createSpeciality(@ModelAttribute("createSpecialityForm") Speciality createSpecialityForm,
			BindingResult bindingResult) {
		specialityValidator.validate(createSpecialityForm, bindingResult);

		if (bindingResult.hasErrors()) {

			ModelAndView model = new ModelAndView();
			model.addObject("createSpecialityPage", createSpecialityForm);
			model.setViewName("createSpecialityPage");
			return model;
		}

		long createstatus = specialityService.save(createSpecialityForm);
		ModelAndView model = new ModelAndView();

		if (createstatus > 0) {
			model.addObject("specialityName", createSpecialityForm.getName());
		}

		model.addObject("createSpecialityForm", new Speciality());
		model.setViewName("createSpecialityPage");
		return model;
	}

	@GetMapping(value = "/gridViewSpeciality")
	public ModelAndView gridViewSpeciality(@RequestParam(required = false) String message) {
		final ModelAndView model = new ModelAndView("gridViewSpecialityPage");
		List<Speciality> specialities = specialityService.findAll();
		model.addObject("specialityGridData", specialities);
		model.addObject("message", message);

		return model;
	}

	@RequestMapping(value = "/getSpeciality/{id}", method = RequestMethod.GET)
	public ModelAndView getSpecialityById(@PathVariable long id) {
		ModelAndView model = new ModelAndView();

		Speciality speciality = specialityService.findById(id);
		model.addObject("updateSpecialityForm", speciality);
		model.setViewName("updateSpecialityPage");

		return model;
	}

	@PostMapping("/updateSpeciality")
	public ModelAndView updateSpeciality(@ModelAttribute("updateSpecialityForm") Speciality updateSpecialityForm,
			BindingResult bindingResult) {

		specialityValidator.validate(updateSpecialityForm, bindingResult);

		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView();
			model.addObject("updateSpecialityForm", updateSpecialityForm);
			model.setViewName("updateSpecialityPage");
			return model;
		}

		System.out.println("updateSpecialityForm : " + updateSpecialityForm);

		long updateStatus = specialityService.update(updateSpecialityForm, updateSpecialityForm.getId());

		ModelAndView model = new ModelAndView();

		Speciality speciality = specialityService.findById(updateSpecialityForm.getId());
		if (updateStatus > 0) {
			model.addObject("specialityName", speciality.getName());
		}

		model.addObject("updateSpecialityForm", speciality);
		model.setViewName("updateSpecialityPage");

		return model;
	}

	@RequestMapping(value = "/deleteSpeciality/{id}", method = RequestMethod.GET)
	public ModelAndView deleteSpecialityId(@PathVariable("id") long id) {

		logger.info("id {} : ", id);
		long deletestatus = specialityService.deleteById(id);
		logger.info("deletestatus {} : ", deletestatus);

		String message = "FAILED";

		if (deletestatus == -1) {

			message = "INUSED";

		} else if (deletestatus == 1) {

			message = "SUCCESS";

		}

		return new ModelAndView("redirect:/gridViewSpeciality?message=" + message);
	}

}
