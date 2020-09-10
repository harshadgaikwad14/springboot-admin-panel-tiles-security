package com.example.service;

import java.util.List;

import com.example.model.SpecialityCaptchaDTO;

public interface SpecialityCaptchaService {
	public long save(SpecialityCaptchaDTO specialityCaptchaDTO);
	List<SpecialityCaptchaDTO> findAll();
	
	public int updateById(SpecialityCaptchaDTO specialityCaptchaDTO);

	SpecialityCaptchaDTO findById(long specialityCaptchaid);

	SpecialityCaptchaDTO findByCaptchaTxtAndSpecialityId(final String captchaTxt, long specialityId);

	List<SpecialityCaptchaDTO> findAllBySpecialityId(long specialityId);

	int deleteById(long id);

}
