package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.EventImageDTO;
import com.example.model.EventShare;
import com.example.repository.EventImageRepository;
import com.example.repository.EventShareRepository;

@Service
public class EventShareServiceImpl implements EventShareService {
	
	@Autowired
	private EventShareRepository eventShareRepository;

	@Override
	public long save(EventShare eventShare) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EventShare> findAll() {
		// TODO Auto-generated method stub
		return eventShareRepository.findAll();
	}
	
}
