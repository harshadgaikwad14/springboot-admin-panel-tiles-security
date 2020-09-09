package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.EventImageDTO;
import com.example.repository.EventImageRepository;

@Service
public class EventImageServiceImpl implements EventImageService {
	@Autowired
	private EventImageRepository eventImageRepository;

	@Override
	public long save(EventImageDTO eventImageDTO) {
		// TODO Auto-generated method stub
		return eventImageRepository.save(eventImageDTO);
	}

	@Override
	public long update(EventImageDTO eventImageDTO, long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EventImageDTO findById(long eventImageId) {
		// TODO Auto-generated method stub
		return eventImageRepository.findById(eventImageId);
	}

	@Override
	public List<EventImageDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventImageDTO> findByEventId(long eventId) {
		// TODO Auto-generated method stub
		return eventImageRepository.findByEventId(eventId);
	}

	@Override
	public void deleteById(long eventImageId) {
		// TODO Auto-generated method stub
		eventImageRepository.deleteById(eventImageId);
	}

}
