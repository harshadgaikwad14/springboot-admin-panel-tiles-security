package com.example.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.Speciality;

@Component
public class SpecialityRowMapper implements RowMapper<Speciality> {

	public Speciality mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Speciality speciality = new Speciality();
		speciality.setId(rs.getLong("id"));

		speciality.setName(rs.getString("name"));

		return speciality;
	}

}