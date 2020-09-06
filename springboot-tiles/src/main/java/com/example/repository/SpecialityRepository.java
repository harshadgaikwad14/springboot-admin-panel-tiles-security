package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Speciality;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

	Speciality findByName(final String name);
}
