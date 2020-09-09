package com.example.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.EventDTO;

@Component
public class EventRowMapper implements RowMapper<EventDTO> {

	public EventDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final EventDTO eventDTO = new EventDTO();
		eventDTO.setId(rs.getLong("id"));
		eventDTO.setName(rs.getString("event_name"));
		eventDTO.setEventDate(rs.getDate("event_date").toString());
		return eventDTO;
	}

}