package com.example.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.ClientRegistration;
import com.example.model.Speciality;

@Component
public class ClientRegistrationRowMapper implements RowMapper<ClientRegistration> {

	public ClientRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
		final ClientRegistration clientRegistration = new ClientRegistration();
		clientRegistration.setId(rs.getLong("id"));
		clientRegistration.setDrName(rs.getString("dr_name"));
		clientRegistration.setMobileNo(rs.getString("mobile_no"));
		clientRegistration.setEmailId(rs.getString("email_id"));
		clientRegistration.setSpeciality(rs.getLong("speciality_id"));
		clientRegistration.setCity(rs.getString("city"));
		clientRegistration.setState(rs.getString("state"));
		clientRegistration.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());

		return clientRegistration;
	}
	

}