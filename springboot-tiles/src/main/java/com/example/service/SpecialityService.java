package com.example.service;

import java.util.List;

import com.example.model.Speciality;

public interface SpecialityService {
	void save(Speciality speciality);

	Speciality findByName(String name);

	List<Speciality> findAll();

	Speciality findById(long id);

	void deleteById(long id);
	
	 
}
