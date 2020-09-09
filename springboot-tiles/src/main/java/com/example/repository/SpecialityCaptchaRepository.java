package com.example.repository;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.model.Role;
import com.example.model.SpecialityCaptchaDTO;
import com.example.rowMapper.SpecialityCaptchaRowMapper;

@Repository
public class SpecialityCaptchaRepository {

	Logger logger = LoggerFactory.getLogger(SpecialityCaptchaRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private SpecialityCaptchaRowMapper specialityCaptchaRowMapper;

	public List<SpecialityCaptchaDTO> findAll() {
		final String userQuery = "SELECT sc.id as id, captcha_txt, captcha_img_url, speciality_id ,s.name as specialityName FROM spciality_captcha sc left outer join speciality s on sc.speciality_id = s.id";

		try {
			final List<SpecialityCaptchaDTO> specialityCaptchaDTs = namedParameterJdbcTemplate.query(userQuery,
					specialityCaptchaRowMapper);
			logger.info("specialityCaptchaDTs : {} ", specialityCaptchaDTs);
			return specialityCaptchaDTs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<SpecialityCaptchaDTO> findAllBySpecialityId(long specialityId) {
		final String userQuery = "SELECT sc.id as id, captcha_txt, captcha_img_url, speciality_id ,s.name as specialityName FROM spciality_captcha sc left outer join speciality s on sc.speciality_id = s.id WHERE  sc.speciality_id=:specialityId";

		final SqlParameterSource parameters = new MapSqlParameterSource("specialityId", specialityId);
		try {
			final List<SpecialityCaptchaDTO> specialityCaptchaDTs = namedParameterJdbcTemplate.query(userQuery,
					parameters, specialityCaptchaRowMapper);
			logger.info("specialityCaptchaDTs : {} ", specialityCaptchaDTs);
			return specialityCaptchaDTs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public SpecialityCaptchaDTO findByCaptchaTxtAndSpecialityId(final String captchaTxt, long specialityId) {
		final String userQuery = "SELECT sc.id as id, captcha_txt, captcha_img_url, speciality_id ,s.name as specialityName FROM spciality_captcha sc left outer join speciality s on sc.speciality_id = s.id WHERE  captcha_txt=:captchaTxt and sc.speciality_id=:specialityId";

		final SqlParameterSource parameters = new MapSqlParameterSource("specialityId", specialityId)
				.addValue("captchaTxt", captchaTxt);
		try {
			final SpecialityCaptchaDTO specialityCaptchaDTO = namedParameterJdbcTemplate.queryForObject(userQuery,
					parameters, specialityCaptchaRowMapper);
			logger.info("specialityCaptchaDTO : {} ", specialityCaptchaDTO);
			return specialityCaptchaDTO;
		} catch (Exception e) {

		}

		return null;

	}

	public SpecialityCaptchaDTO findById(long specialityCaptchaId) {
		final String userQuery = "SELECT sc.id as id, captcha_txt, captcha_img_url, speciality_id ,s.name as specialityName FROM spciality_captcha sc left outer join speciality s on sc.speciality_id = s.id WHERE  sc.id=:specialityCaptchaId";

		final SqlParameterSource parameters = new MapSqlParameterSource("specialityCaptchaId", specialityCaptchaId);
		try {
			final SpecialityCaptchaDTO specialityCaptchaDTO = namedParameterJdbcTemplate.queryForObject(userQuery,
					parameters, specialityCaptchaRowMapper);
			logger.info("specialityCaptchaDTO : {} ", specialityCaptchaDTO);
			return specialityCaptchaDTO;
		} catch (Exception e) {

		}

		return null;

	}

	@Transactional
	public long save(SpecialityCaptchaDTO specialityCaptchaDTO) {
		logger.info("specialityCaptchaDTO : {} ", specialityCaptchaDTO);

		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final String userQuery = "INSERT INTO spciality_captcha (captcha_txt, captcha_img_url, speciality_id) VALUES (:captchaTxt, :captchaImgUrl, :specialityId)";

		final SqlParameterSource parameters = new MapSqlParameterSource("captchaTxt",
				specialityCaptchaDTO.getCaptchaText())
						.addValue("captchaImgUrl", specialityCaptchaDTO.getCaptchaImagePath())
						.addValue("specialityId", specialityCaptchaDTO.getSpeciality());

		final int createStatus = namedParameterJdbcTemplate.update(userQuery, parameters, keyHolder);
		logger.info("createStatus : {} ", createStatus);
		return keyHolder.getKey().longValue();
	}

	public int updateById(SpecialityCaptchaDTO specialityCaptchaDTO) {

		logger.info("updateById - specialityCaptchaDTO : {}", specialityCaptchaDTO);

		SpecialityCaptchaDTO exSpecialityCaptchaDTO = findById(specialityCaptchaDTO.getId());
		logger.info(">> updateById - exSpecialityCaptchaDTO : {}", exSpecialityCaptchaDTO);
		if (exSpecialityCaptchaDTO != null) {
			if (StringUtils.isEmpty(specialityCaptchaDTO.getCaptchaText())) {
				specialityCaptchaDTO.setCaptchaText(exSpecialityCaptchaDTO.getCaptchaText());
			}

			if (specialityCaptchaDTO.getId() < 1) {
				specialityCaptchaDTO.setSpeciality(exSpecialityCaptchaDTO.getSpeciality());
			}

			if (StringUtils.isEmpty(specialityCaptchaDTO.getCaptchaImagePath())) {
				specialityCaptchaDTO.setCaptchaImagePath(exSpecialityCaptchaDTO.getCaptchaImagePath());
			}
		}

		final String userQuery = "UPDATE spciality_captcha SET captcha_txt=:captchaTxt, captcha_img_url=:captchaImgUrl, speciality_id=:specialityId WHERE  id=:id";

		final SqlParameterSource parameters = new MapSqlParameterSource("captchaTxt",
				specialityCaptchaDTO.getCaptchaText())
						.addValue("captchaImgUrl", specialityCaptchaDTO.getCaptchaImagePath())
						.addValue("specialityId", specialityCaptchaDTO.getSpeciality())
						.addValue("id", specialityCaptchaDTO.getId());

		final int updateStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("updateStatus : {} ", updateStatus);
		return updateStatus;
	}

	public int deleteById(long specialityCaptchaId) {

		SpecialityCaptchaDTO exSpecialityCaptchaDTO = findById(specialityCaptchaId);

		if (exSpecialityCaptchaDTO != null) {
			return -1;
		}

		final String userQuery = "DELETE FROM spciality_captcha WHERE id=:specialityCaptchaId";

		final SqlParameterSource parameters = new MapSqlParameterSource("specialityCaptchaId", specialityCaptchaId);

		final int deleteStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("deleteStatus : {} ", deleteStatus);
		return deleteStatus;
	}
}
