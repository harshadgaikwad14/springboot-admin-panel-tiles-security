package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Speciality;
import com.example.model.SpecialityCaptcha;
import com.example.model.SpecialityCaptchaDTO;
import com.example.service.SpecialityCaptchaService;
import com.example.service.SpecialityService;
import com.example.validator.SpecialityCaptchaValidator;
import com.example.validator.UpdateSpecialityCaptchaValidator;

@Controller
public class SpecialityCaptchaController {

	Logger logger = LoggerFactory.getLogger(SpecialityCaptchaController.class);

	@Autowired
	private SpecialityCaptchaService specialityCaptchaService;

	@Autowired
	private SpecialityService specialityService;

	@Autowired
	private SpecialityCaptchaValidator specialityCaptchaValidator;
	
	@Autowired
	private UpdateSpecialityCaptchaValidator updateSpecialityCaptchaValidator;

	@GetMapping("/createSpecialityCaptcha")
	public String getCreateSpecialityCaptchat(Model model) {
		logger.info("getCreateSpecialityCaptchat - get called");
		model.addAttribute("createSpecialityCaptchaForm", new SpecialityCaptcha());

		List<Speciality> specialities = specialityService.findAll();

		Map<Long, String> specialityMap = specialities.stream()
				.collect(Collectors.toMap(Speciality::getId, Speciality::getName));

		model.addAttribute("specialityMap", specialityMap);
		return "createSpecialityCaptchaPage";
	}

	@PostMapping("/createSpecialityCaptcha")
	public ModelAndView createSpecialityCaptcha(HttpServletRequest servletRequest,
			@ModelAttribute("createSpecialityCaptchaForm") SpecialityCaptcha createSpecialityCaptchaForm,
			BindingResult bindingResult) {

		logger.info("createSpecialityCaptchaForm - createSpecialityCaptchaForm : {}", createSpecialityCaptchaForm);
		List<Speciality> specialities = specialityService.findAll();

		Map<Long, String> specialityMap = specialities.stream()
				.collect(Collectors.toMap(Speciality::getId, Speciality::getName));
		specialityCaptchaValidator.validate(createSpecialityCaptchaForm, bindingResult);

		if (bindingResult.hasErrors()) {

			ModelAndView model = new ModelAndView();
			model.addObject("createSpecialityCaptchaForm", createSpecialityCaptchaForm);

			model.addObject("specialityMap", specialityMap);
			model.setViewName("createSpecialityCaptchaPage");
			return model;
		}

		// Get the uploaded files and store them
		MultipartFile file = createSpecialityCaptchaForm.getImage();
		String fileNamePath = null;
		final String uniquePath = UUID.randomUUID().toString();
		if (null != file) {

			String fileName = file.getOriginalFilename();

			fileNamePath = "/static/images/captcha/" + uniquePath + "/" + fileName;

			File imageDir = new File(servletRequest.getServletContext().getRealPath("static"),
					"images/captcha/" + uniquePath);
			logger.info("createSpecialityCaptcha - imageDir : {}", imageDir);
			imageDir.mkdirs();
			File imageFile = new File(imageDir, fileName);

			logger.info("createSpecialityCaptcha - imageFile : {}", imageFile);
			try {
				/* create actual file */
				imageFile.createNewFile();
				file.transferTo(imageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		SpecialityCaptchaDTO specialityCaptchaDTO = new SpecialityCaptchaDTO();
		specialityCaptchaDTO.setCaptchaText(createSpecialityCaptchaForm.getCaptchaText());
		specialityCaptchaDTO.setSpeciality(createSpecialityCaptchaForm.getSpeciality());
		specialityCaptchaDTO.setCaptchaImagePath(fileNamePath);

		final long eventId = specialityCaptchaService.save(specialityCaptchaDTO);

		ModelAndView model = new ModelAndView();
		if (eventId > 0) {

			model.addObject("generatedCaaptchaName", createSpecialityCaptchaForm.getCaptchaText());
		}
		model.addObject("createSpecialityCaptchaForm", new SpecialityCaptcha());

		model.addObject("specialityMap", specialityMap);
		model.setViewName("createSpecialityCaptchaPage");

		return model;
	}
	
	
	@PostMapping("/updateSpecialityCaptcha")
	public ModelAndView updateSpecialityCaptcha(HttpServletRequest servletRequest,
			@ModelAttribute("updateSpecialityCaptchaForm") SpecialityCaptcha updateSpecialityCaptchaForm,
			BindingResult bindingResult) {

		logger.info("updateSpecialityCaptcha - updateSpecialityCaptchaForm : {}", updateSpecialityCaptchaForm);
		List<Speciality> specialities = specialityService.findAll();

		Map<Long, String> specialityMap = specialities.stream()
				.collect(Collectors.toMap(Speciality::getId, Speciality::getName));
		updateSpecialityCaptchaValidator.validate(updateSpecialityCaptchaForm, bindingResult);

		if (bindingResult.hasErrors()) {

			ModelAndView model = new ModelAndView();
			model.addObject("updateSpecialityCaptchaForm", updateSpecialityCaptchaForm);

			model.addObject("specialityMap", specialityMap);
			model.setViewName("updateSpecialityCaptchaPage");
			return model;
		}

		// Get the uploaded files and store them
		MultipartFile file = updateSpecialityCaptchaForm.getImage();
		String fileNamePath = null;
		final String uniquePath = UUID.randomUUID().toString();
		if (null != file) {

			String fileName = file.getOriginalFilename();
			
			if(!StringUtils.isEmpty(fileName))
			{
				logger.info("####### updateSpecialityCaptcha - fileName : {}", fileName);
				fileNamePath = "/static/images/captcha/" + uniquePath + "/" + fileName;

				File imageDir = new File(servletRequest.getServletContext().getRealPath("static"),
						"images/captcha/" + uniquePath);
				logger.info("createSpecialityCaptcha - imageDir : {}", imageDir);
				imageDir.mkdirs();
				File imageFile = new File(imageDir, fileName);

				logger.info("createSpecialityCaptcha - imageFile : {}", imageFile);
				try {
					/* create actual file */
					imageFile.createNewFile();
					file.transferTo(imageFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			

		}

		SpecialityCaptchaDTO specialityCaptchaDTO = new SpecialityCaptchaDTO();
		updateSpecialityCaptchaForm.setCaptchaImagePath(fileNamePath);
		specialityCaptchaDTO.setId(updateSpecialityCaptchaForm.getId());
		specialityCaptchaDTO.setCaptchaText(updateSpecialityCaptchaForm.getCaptchaText());
		specialityCaptchaDTO.setSpeciality(updateSpecialityCaptchaForm.getSpeciality());
		specialityCaptchaDTO.setCaptchaImagePath(fileNamePath);

		final long updateStatus = specialityCaptchaService.updateById(specialityCaptchaDTO);

		ModelAndView model = new ModelAndView();
		if (updateStatus > 0) {

			model.addObject("updatedCaptchaName", updateSpecialityCaptchaForm.getCaptchaText());
		}
		
		
		SpecialityCaptchaDTO updatedSpecialityCaptchaDTO = specialityCaptchaService.findById(updateSpecialityCaptchaForm.getId());
		logger.info("getSpecialityCaptcha - specialityCaptchaDTO : {}", updatedSpecialityCaptchaDTO);
		SpecialityCaptcha specialityCaptcha = new SpecialityCaptcha();
		specialityCaptcha.setId(updatedSpecialityCaptchaDTO.getId());
		specialityCaptcha.setCaptchaText(updatedSpecialityCaptchaDTO.getCaptchaText());
		specialityCaptcha.setSpeciality(updatedSpecialityCaptchaDTO.getSpeciality());
		specialityCaptcha.setCaptchaImagePath(updatedSpecialityCaptchaDTO.getCaptchaImagePath());
		
		model.addObject("updateSpecialityCaptchaForm", specialityCaptcha);
		

		model.addObject("specialityMap", specialityMap);
		
		model.setViewName("updateSpecialityCaptchaPage");
		return model;
	}
	
	@RequestMapping(value = "/getSpecialityCaptcha/{id}", method = RequestMethod.GET)
	public ModelAndView getSpecialityCaptcha(@PathVariable long id) {
		
		
		logger.info("getSpecialityCaptcha - id : {}", id);
		
		List<Speciality> specialities = specialityService.findAll();

		Map<Long, String> specialityMap = specialities.stream()
				.collect(Collectors.toMap(Speciality::getId, Speciality::getName));
		
		ModelAndView model = new ModelAndView();

		SpecialityCaptchaDTO specialityCaptchaDTO = specialityCaptchaService.findById(id);
		logger.info("getSpecialityCaptcha - specialityCaptchaDTO : {}", specialityCaptchaDTO);
		SpecialityCaptcha specialityCaptcha = new SpecialityCaptcha();
		specialityCaptcha.setId(specialityCaptchaDTO.getId());
		specialityCaptcha.setCaptchaText(specialityCaptchaDTO.getCaptchaText());
		specialityCaptcha.setSpeciality(specialityCaptchaDTO.getSpeciality());
		specialityCaptcha.setCaptchaImagePath(specialityCaptchaDTO.getCaptchaImagePath());
		model.addObject("specialityMap", specialityMap);
		model.addObject("updateSpecialityCaptchaForm", specialityCaptcha);
		model.setViewName("updateSpecialityCaptchaPage");

		return model;
	}

	@GetMapping(value = "/gridViewSpecialityCaptcha")
	public ModelAndView inputProduct() {
		final ModelAndView model = new ModelAndView("gridViewSpecialityCaptchaPage");

		final List<SpecialityCaptchaDTO> specialityCaptchaList = specialityCaptchaService.findAll();
		
		model.addObject("specialityCaptchaGridData", specialityCaptchaList);

		return model;
	}
	
	@GetMapping(value = "/deleteSpecialityCaptcha/{id}")
	public ModelAndView deleteSpecialityCaptcha(@PathVariable("id") long id) {

		logger.info("deleteSpecialityCaptcha - id {} : ", id);
		specialityCaptchaService.deleteById(id);

		return new ModelAndView("redirect:/gridViewSpecialityCaptcha");
	}
	

}
