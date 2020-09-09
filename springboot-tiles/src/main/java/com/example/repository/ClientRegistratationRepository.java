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

import com.example.model.ClientRegistration;
import com.example.model.Role;
import com.example.rowMapper.ClientRegistrationRowMapper;

@Repository
public class ClientRegistratationRepository {

	Logger logger = LoggerFactory.getLogger(ClientRegistratationRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private ClientRegistrationRowMapper clientRegistrationRowMapper;

	public List<ClientRegistration> findByAll() {
		final String userQuery = "SELECT cr.id, dr_name, speciality_id, s.id,s.name as speciality_name, email_id, mobile_no, city, state, modified_at \r\n"
				+ "FROM client_registration cr left outer join speciality s \r\n" + "on cr.speciality_id=s.id";

		try {
			final List<ClientRegistration> clientRegistrations = namedParameterJdbcTemplate.query(userQuery,
					clientRegistrationRowMapper);
			logger.info("clientRegistrations : {} ", clientRegistrations);
			return clientRegistrations;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public ClientRegistration findById(long id) {
		final String userQuery = "SELECT cr.id, dr_name, speciality_id, s.id,s.name as speciality_name, email_id, mobile_no, city, state, modified_at \r\n"
				+ "FROM client_registration cr left outer join speciality s \r\n" + "on cr.speciality_id=s.id\r\n"
				+ "WHERE  cr.id=:id";

		final SqlParameterSource parameters = new MapSqlParameterSource("id", id);
		try {
			final ClientRegistration clientRegistration = namedParameterJdbcTemplate.queryForObject(userQuery,
					parameters, clientRegistrationRowMapper);
			logger.info("clientRegistration : {} ", clientRegistration);
			return clientRegistration;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ClientRegistration findByEmailAndMobile(String emailId,String mobileNo) {
		final String userQuery = "SELECT cr.id, dr_name, speciality_id, s.id,s.name as speciality_name, email_id, mobile_no, city, state, modified_at \r\n"
				+ "FROM client_registration cr left outer join speciality s \r\n" + "on cr.speciality_id=s.id\r\n"
				+ "WHERE  email_id=:emailId and mobile_no=:mobileNo";

		final SqlParameterSource parameters = new MapSqlParameterSource("emailId", emailId).addValue("mobileNo", mobileNo);
		try {
			final ClientRegistration clientRegistration = namedParameterJdbcTemplate.queryForObject(userQuery,
					parameters, clientRegistrationRowMapper);
			logger.info("clientRegistration : {} ", clientRegistration);
			return clientRegistration;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	@Transactional
	public long save(ClientRegistration clientRegistration) {
		logger.info("clientRegistration : {} ", clientRegistration);
		ClientRegistration exClientRegistration = findByEmailAndMobile(clientRegistration.getEmailId(), clientRegistration.getMobileNo());
		logger.info("exClientRegistration : {} ", exClientRegistration);
		if (exClientRegistration != null) {
			return exClientRegistration.getId();
		}

		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final String query = "INSERT INTO client_registration (dr_name, speciality_id, email_id, mobile_no, city, state) VALUES (:dr_name, :speciality_id, :email_id, :mobile_no, :city, :state)";

		final SqlParameterSource parameters = new MapSqlParameterSource("dr_name", clientRegistration.getDrName())
				.addValue("speciality_id", clientRegistration.getSpeciality())
				.addValue("email_id", clientRegistration.getEmailId())
				.addValue("mobile_no", clientRegistration.getMobileNo()).addValue("city", clientRegistration.getCity())
				.addValue("state", clientRegistration.getState());

		final int createStatus = namedParameterJdbcTemplate.update(query, parameters, keyHolder);
		logger.info("createStatus : {} ", createStatus);
		return keyHolder.getKey().longValue();
	}

	public int updateById(long roleId, Role role) {
		final String userQuery = "UPDATE role SET name=:roleName WHERE  id=:roleId";

		final SqlParameterSource parameters = new MapSqlParameterSource("roleId", roleId).addValue("roleName",
				role.getName());

		final int updateStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("updateStatus : {} ", updateStatus);
		return updateStatus;
	}

	public int deleteById(long clientRegistrationId) {

		logger.info("clientRegistrationId : {} ", clientRegistrationId);
		ClientRegistration exClientRegistration = findById(clientRegistrationId);
		logger.info("exClientRegistration : {} ", exClientRegistration);
		if (exClientRegistration != null) {
			return -1;
		}

		final String userQuery = "DELETE FROM client_registration WHERE id=:clientRegistrationId";

		final SqlParameterSource parameters = new MapSqlParameterSource("clientRegistrationId", clientRegistrationId);

		final int deleteStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("deleteStatus : {} ", deleteStatus);
		return deleteStatus;
	}
}
