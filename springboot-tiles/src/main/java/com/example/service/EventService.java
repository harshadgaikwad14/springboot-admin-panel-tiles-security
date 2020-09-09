package com.example.service;

import java.util.List;

import com.example.model.EventDTO;

public interface EventService {
	long save(EventDTO eventDTO);


	EventDTO findByNameAndDate(String name,final String date);

	List<EventDTO> findAll();

	EventDTO findById(long id);

	int deleteById(long id);
	int deleteByEventImageId(long eventId,long imageId);
	
	int update(EventDTO eventDTO);

}
