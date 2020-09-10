package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Speciality;
import com.example.repository.SpecialityRepository;

@Service
public class SpecialityServiceImpl implements SpecialityService {
	@Autowired
	private SpecialityRepository specialityRepository;

	@Override
	public long save(Speciality speciality) {

		return specialityRepository.save(speciality);
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
		return specialityRepository.findById(id);
	}

	@Transactional
	@Override
	public long deleteById(long id) {
		return specialityRepository.deleteById(id);
	}

	@Override
	public long update(Speciality speciality, long specialityId) {
		// TODO Auto-generated method stub
		return specialityRepository.updateById(specialityId, speciality);
	}
}
