package com.example.service;

import java.util.List;

import com.example.model.Speciality;

public interface SpecialityService {
	long save(Speciality speciality);

	long update(Speciality speciality, final long id);

	Speciality findByName(String name);

	List<Speciality> findAll();

	Speciality findById(long id);

	void deleteById(long id);

}
