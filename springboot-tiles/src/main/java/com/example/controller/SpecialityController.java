package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Speciality;
import com.example.service.SpecialityService;
import com.example.validator.SpecialityValidator;

@Controller
public class SpecialityController {

	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private SpecialityValidator specialityValidator;

	@GetMapping("/createSpeciality")
	public String registration(Model model) {

		model.addAttribute("createSpecialityForm", new Speciality());

		return "createSpecialityPage";
	}

	@PostMapping("/createSpeciality")
	public ModelAndView registration(@ModelAttribute("createSpecialityForm") Speciality createSpecialityForm,
			BindingResult bindingResult) {
		specialityValidator.validate(createSpecialityForm, bindingResult);

		if (bindingResult.hasErrors()) {

			ModelAndView model = new ModelAndView();
			model.addObject("createSpecialityPage", createSpecialityForm);
			model.setViewName("createSpecialityPage");
			return model;
		}

		specialityService.save(createSpecialityForm);
		ModelAndView model = new ModelAndView();

		model.addObject("createSpecialityForm", new Speciality());
		model.setViewName("createSpecialityPage");
		return model;
	}

	@GetMapping(value = "/gridViewSpeciality")
	public ModelAndView list() {
		final ModelAndView model = new ModelAndView("gridViewSpecialityPage");
		List<Speciality> specialities = specialityService.findAll();
		model.addObject("specialityGridData", specialities);

		return model;
	}

	@RequestMapping(value = "/getSpeciality/{id}", method = RequestMethod.GET)
	public ModelAndView specialityById(@PathVariable long id) {
		ModelAndView model = new ModelAndView();

		Speciality speciality = specialityService.findById(id);
		model.addObject("updateSpecialityForm", speciality);
		model.setViewName("updateSpecialityPage");

		return model;
	}

	@PostMapping("/updateSpeciality")
	public ModelAndView updateSpecialityById(@ModelAttribute("updateSpecialityForm") Speciality updateSpecialityForm,
			BindingResult bindingResult) {

		specialityValidator.validate(updateSpecialityForm, bindingResult);

		if (bindingResult.hasErrors()) {
			ModelAndView model = new ModelAndView();
			model.addObject("updateSpecialityForm", updateSpecialityForm);
			model.setViewName("updateSpecialityPage");
			return model;
		}
		
		System.out.println("updateSpecialityForm : "+updateSpecialityForm);

		specialityService.save(updateSpecialityForm);

		return new ModelAndView("redirect:/gridViewSpeciality");
	}

	@RequestMapping(value = "/deleteSpeciality/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") long id) {
		specialityService.deleteById(id);

		return new ModelAndView("redirect:/gridViewSpeciality");
	}

}
