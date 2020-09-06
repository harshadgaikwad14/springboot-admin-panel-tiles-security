package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Speciality;
import com.example.repository.SpecialityRepository;

@Service
public class SpecialityServiceImpl implements SpecialityService {
	@Autowired
	private SpecialityRepository specialityRepository;

	@Override
	public void save(Speciality speciality) {

		specialityRepository.save(speciality);
	}

	@Override
	public Speciality findByName(String name) {
		return specialityRepository.findByName(name);

	}
	@Override
	public List<Speciality> findAll() {
		return specialityRepository.findAll();
	}
	@Override
	public Speciality findById(final long id) {
		return specialityRepository.findById(id).get();
	}
	@Override
	public void deleteById(long id) {
		specialityRepository.deleteById(id);
	}
}
