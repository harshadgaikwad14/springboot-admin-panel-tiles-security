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

import com.example.model.Speciality;
import com.example.rowMapper.SpecialityRowMapper;

@Repository
public class SpecialityRepository {

	Logger logger = LoggerFactory.getLogger(SpecialityRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private SpecialityRowMapper specialityRowMapper;

	public List<Speciality> findAll() {
		final String userQuery = "SELECT id, name FROM speciality";
		try {
			final List<Speciality> specialities = namedParameterJdbcTemplate.query(userQuery, specialityRowMapper);
			logger.info("specialities : {} ", specialities);
			return specialities;
		} catch (Exception e) {

		}

		return Collections.emptyList();
	}

	public Speciality findByName(String name) {
		final String userQuery = "SELECT id, name FROM speciality WHERE  name=:name";

		try {
			final SqlParameterSource parameters = new MapSqlParameterSource("name", name);

			final Speciality speciality = namedParameterJdbcTemplate.queryForObject(userQuery, parameters,
					specialityRowMapper);
			logger.info("speciality : {} ", speciality);
			return speciality;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public Speciality findById(long specialityId) {
		logger.info("findById : specialityId : {} ", specialityId);
		final String userQuery = "SELECT id, name FROM speciality WHERE  id=:specialityId";

		try {
			final SqlParameterSource parameters = new MapSqlParameterSource("specialityId", specialityId);

			final Speciality speciality = namedParameterJdbcTemplate.queryForObject(userQuery, parameters,
					specialityRowMapper);
			logger.info("speciality : {} ", speciality);
			return speciality;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public long save(Speciality speciality) {

		Speciality exSpeciality = findByName(speciality.getName());
		if (exSpeciality != null) {
			return exSpeciality.getId();
		}
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final String userQuery = "INSERT INTO speciality (name) VALUES (:name)";

		final SqlParameterSource parameters = new MapSqlParameterSource("name", speciality.getName());

		final int createStatus = namedParameterJdbcTemplate.update(userQuery, parameters, keyHolder);
		logger.info("createStatus : {} ", createStatus);
		return keyHolder.getKey().longValue();
	}

	public int updateById(long specialityId, Speciality speciality) {
		final String userQuery = "UPDATE speciality SET name=:name WHERE  id=:specialityId";

		final SqlParameterSource parameters = new MapSqlParameterSource("specialityId", specialityId).addValue("name",
				speciality.getName());

		final int updateStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("updateStatus : {} ", updateStatus);
		return updateStatus;
	}

	
	public int deleteById(long specialityId) {
		logger.info("deleteById : specialityId : {} ", specialityId);
		Speciality exSpeciality = findById(specialityId);
		logger.info("exSpeciality : {} ", exSpeciality);
		if (exSpeciality == null) {
			return 0;
		}
		
		

		final String userQuery = "DELETE FROM speciality WHERE  id=:specialityId";

		final SqlParameterSource parameters = new MapSqlParameterSource("specialityId", specialityId);

		final int deleteStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("deleteStatus : {} ", deleteStatus);
		return deleteStatus;
	}
}
