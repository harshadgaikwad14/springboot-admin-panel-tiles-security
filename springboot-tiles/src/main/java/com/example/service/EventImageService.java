package com.example.service;

import java.util.List;

import com.example.model.EventImageDTO;

public interface EventImageService {
	public long save(EventImageDTO eventImageDTO);

	long update(EventImageDTO eventImageDTO, final long id);

	EventImageDTO findById(long eventImageId);

	List<EventImageDTO> findAll();

	List<EventImageDTO> findByEventId(long eventId);

	void deleteById(long id);

}
