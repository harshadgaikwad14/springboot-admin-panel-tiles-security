package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.SpecialityCaptchaDTO;
import com.example.repository.SpecialityCaptchaRepository;

@Service
public class SpecialityCaptchaServiceImpl implements SpecialityCaptchaService {
	@Autowired
	private SpecialityCaptchaRepository specialityCaptchaRepository;

	@Override
	public long save(SpecialityCaptchaDTO specialityCaptchaDTO) {
		// TODO Auto-generated method stub
		return specialityCaptchaRepository.save(specialityCaptchaDTO);
	}

	@Override
	public SpecialityCaptchaDTO findById(long specialityCaptchaId) {
		// TODO Auto-generated method stub
		return specialityCaptchaRepository.findById(specialityCaptchaId);
	}

	@Override
	public List<SpecialityCaptchaDTO> findAllBySpecialityId(long specialityId) {
		// TODO Auto-generated method stub
		return specialityCaptchaRepository.findAllBySpecialityId(specialityId);
	}

	@Override
	public void deleteById(long specialityCaptchaId) {
		// TODO Auto-generated method stub
		specialityCaptchaRepository.deleteById(specialityCaptchaId);
	}

	@Override
	public SpecialityCaptchaDTO findByCaptchaTxtAndSpecialityId(String captchaTxt, long specialityId) {
		// TODO Auto-generated method stub
		return specialityCaptchaRepository.findByCaptchaTxtAndSpecialityId(captchaTxt, specialityId);
	}

	@Override
	public List<SpecialityCaptchaDTO> findAll() {
		// TODO Auto-generated method stub
		return specialityCaptchaRepository.findAll();
	}

	@Override
	public int updateById(SpecialityCaptchaDTO specialityCaptchaDTO) {
		// TODO Auto-generated method stub
		return specialityCaptchaRepository.updateById(specialityCaptchaDTO);
	}

}
