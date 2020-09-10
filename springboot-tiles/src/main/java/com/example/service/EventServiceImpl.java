package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.EventDTO;
import com.example.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventRepository eventRepository;

	@Override
	public long save(EventDTO eventDTO) {

		return eventRepository.save(eventDTO);
	}

	@Override
	public EventDTO findByNameAndDate(String eventName, String eventDate) {
		return eventRepository.findByNameAndDate(eventName, eventDate);

	}

	@Override
	public List<EventDTO> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public EventDTO findById(final long id) {
		return eventRepository.findById(id);
	}

	
	@Override
	public int deleteById(long id) {
		return eventRepository.deleteById(id);
	}

	

	@Override
	public int deleteByEventImageId(long eventId, long eventImageId) {
		// TODO Auto-generated method stub
		return eventRepository.deleteByEventImageId(eventId, eventImageId);
	}



	@Override
	public int update(EventDTO eventDTO) {
		// TODO Auto-generated method stub
		return eventRepository.update(eventDTO);
	}

	@Override
	public List<EventDTO> findAllByDateRange(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return eventRepository.findAllByDateRange(fromDate, toDate);
	}
}
