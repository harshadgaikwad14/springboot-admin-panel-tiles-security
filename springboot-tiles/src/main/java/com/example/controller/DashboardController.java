package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.EventShare;
import com.example.service.EventShareService;

@Controller
public class DashboardController {

	Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private EventShareService eventShareService;

	@GetMapping(value = { "/", "/home" })
	public ModelAndView gridViewEvent(@RequestParam(required = false) String message) {
		final ModelAndView model = new ModelAndView("dashBoardPage");

		final List<EventShare> eventShares = eventShareService.findAll();

		model.addObject("eventShareGridData", eventShares);

		return model;
	}

}
