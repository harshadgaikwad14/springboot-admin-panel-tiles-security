package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Event;
import com.example.model.EventDTO;
import com.example.model.EventImageDTO;
import com.example.service.EventService;

@Controller
public class EventController {

	private static final Logger logger = LogManager.getLogger(AdminUserController.class);

	@Autowired
	private EventService eventService;

	@GetMapping("/createEvent")
	public String getEvent(Model model) {
		logger.info("get create event page");
		model.addAttribute("createEventForm", new Event());

		return "createEventPage";
	}

	@PostMapping("/createEvent")
	public ModelAndView createEvent(HttpServletRequest servletRequest,
			@ModelAttribute("createEventForm") Event createEventForm) {

		logger.info("save new event : {}", createEventForm);

		// Get the uploaded files and store them
		List<MultipartFile> files = createEventForm.getImages();
		List<String> fileNames = new ArrayList<String>();
		final String uniquePath = UUID.randomUUID().toString();
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();

				fileNames.add("/static/images/upload/" + uniquePath + "/" + fileName);

				/* create image directory **/
				// File imageDir = new
				// File(servletRequest.getServletContext().getRealPath("WEB-INF"), "image");
				File imageDir = new File(servletRequest.getServletContext().getRealPath("static"),
						"images/upload/" + uniquePath);
				logger.info("createEvent - imageDir : {}", imageDir);
				imageDir.mkdirs();
				File imageFile = new File(imageDir, fileName);

				logger.info("createEvent - imageFile : {}", imageFile);
				try {
					/* create actual file */
					imageFile.createNewFile();
					multipartFile.transferTo(imageFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		EventDTO eventDTO = new EventDTO();
		eventDTO.setName(createEventForm.getEventName());
		eventDTO.setEventDate(createEventForm.getEventDate());
		List<EventImageDTO> images = new ArrayList<>();
		for (String fileName : fileNames) {
			EventImageDTO e = new EventImageDTO();
			e.setImageUrl(fileName);
			images.add(e);
		}

		eventDTO.setImages(images);
		final long eventId = eventService.save(eventDTO);

		ModelAndView model = new ModelAndView();
		if (eventId > 0) {

			model.addObject("generatedEventName", createEventForm.getEventName());
		}
		model.addObject("createEventForm", new Event());
		model.setViewName("createEventPage");

		return model;
	}

	@PostMapping("/updateEvent")
	public ModelAndView updateEvent(HttpServletRequest servletRequest,
			@ModelAttribute("updateEventForm") Event updateEventForm) {

		logger.info("update existing event : {}", updateEventForm);

		// Get the uploaded files and store them
		List<MultipartFile> files = updateEventForm.getImages();
		List<String> fileNames = new ArrayList<String>();
		final String uniquePath = UUID.randomUUID().toString();
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {

				String fileName = multipartFile.getOriginalFilename();

				if (!StringUtils.isEmpty(fileName)) {
					fileNames.add("/static/images/upload/" + uniquePath + "/" + fileName);

					
					File imageDir = new File(servletRequest.getServletContext().getRealPath("static"),
							"images/upload/" + uniquePath);
					logger.info("createEvent - imageDir : {}", imageDir);
					imageDir.mkdirs();
					File imageFile = new File(imageDir, fileName);

					logger.info("createEvent - imageFile : {}", imageFile);
					try {
						/* create actual file */
						imageFile.createNewFile();
						multipartFile.transferTo(imageFile);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}

		EventDTO eventDTO = new EventDTO();
		eventDTO.setId(updateEventForm.getId());
		eventDTO.setName(updateEventForm.getEventName());
		eventDTO.setEventDate(updateEventForm.getEventDate());

		if (!CollectionUtils.isEmpty(fileNames)) {
			List<EventImageDTO> images = new ArrayList<>();
			for (String fileName : fileNames) {
				EventImageDTO e = new EventImageDTO();
				e.setEventId(updateEventForm.getId());
				e.setImageUrl(fileName);
				images.add(e);
			}

			eventDTO.setImages(images);
		}

		final long eventId = eventService.update(eventDTO);

		ModelAndView model = new ModelAndView();
		if (eventId > 0) {

			model.addObject("generatedEventName", updateEventForm.getEventName());
		}

		EventDTO exEventDTO = eventService.findById(updateEventForm.getId());
		logger.info("getEvent - eventDTO : {}", eventDTO);
		Event event = new Event();
		event.setId(exEventDTO.getId());
		event.setEventName(exEventDTO.getName());
		event.setEventDate(exEventDTO.getEventDate());

		Map<Long, String> eventImageMap = exEventDTO.getImages().stream()
				.collect(Collectors.toMap(EventImageDTO::getId, EventImageDTO::getImageUrl));

		model.addObject("eventImageMap", eventImageMap);
		model.addObject("updateEventForm", event);
		model.setViewName("updateEventPage");

		return model;
	}

	@RequestMapping(value = "/getEvent/{id}", method = RequestMethod.GET)
	public ModelAndView getEvent(@PathVariable long id, @RequestParam(required = false) String message) {

		logger.info("get event details for id : {}", id);

		final ModelAndView model = new ModelAndView();

		final EventDTO eventDTO = eventService.findById(id);
		logger.debug("getEvent - eventDTO : {}", eventDTO);
		Event event = new Event();
		event.setId(eventDTO.getId());
		event.setEventName(eventDTO.getName());
		event.setEventDate(eventDTO.getEventDate());

		final Map<Long, String> eventImageMap = eventDTO.getImages().stream()
				.collect(Collectors.toMap(EventImageDTO::getId, EventImageDTO::getImageUrl));

		model.addObject("eventImageMap", eventImageMap);
		model.addObject("updateEventForm", event);
		model.addObject("message", message);
		model.setViewName("updateEventPage");

		return model;
	}

	@GetMapping(value = "/gridViewEvent")
	public ModelAndView gridViewEvent(@RequestParam(required = false) String message) {

		logger.info("get event grid data");

		final ModelAndView model = new ModelAndView("gridViewEventPage");

		final List<EventDTO> events = eventService.findAll();

		model.addObject("eventGridData", events);
		model.addObject("message", message);

		return model;
	}

	@GetMapping(value = "/deleteEvent/{id}")
	public ModelAndView deleteEvent(@PathVariable("id") long id) {

		logger.info("delete event for id : {} ", id);
		int deleteStatus = eventService.deleteById(id);
		String message = "FAILED";

		if (deleteStatus == -1) {

			message = "INUSED";

		} else if (deleteStatus == 1) {

			message = "SUCCESS";

		}

		return new ModelAndView("redirect:/gridViewEvent?message=" + message);
	}

	@GetMapping(value = "/deleteEventImage/{id}/{imageId}")
	public ModelAndView deleteEventImage(@PathVariable("id") long id, @PathVariable("imageId") long imageId) {

		logger.info("delete image id : {}  for event id : {} ", imageId, id);

		int deleteStatus = eventService.deleteByEventImageId(id, imageId);
		String message = "DELETE_FAILED";

		if (deleteStatus == -1) {

			message = "INUSED";

		} else if (deleteStatus == 1) {

			message = "DELETE_SUCCESS";

		}

		return new ModelAndView("redirect:/getEvent/" + id + "?message=" + message);
	}

}
