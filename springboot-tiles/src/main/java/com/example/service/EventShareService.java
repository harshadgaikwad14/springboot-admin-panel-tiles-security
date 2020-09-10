package com.example.service;

import java.util.List;

import com.example.model.EventShare;

public interface EventShareService {
	public long save(EventShare eventShare);

	List<EventShare> findAll();

}
