package com.example.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.EventImageDTO;

@Component
public class EventImageRowMapper implements RowMapper<EventImageDTO> {

	public EventImageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final EventImageDTO eventImageDTO = new EventImageDTO();
		eventImageDTO.setId(rs.getLong("id"));
		eventImageDTO.setImageUrl(rs.getString("img_url"));
		eventImageDTO.setEventId(rs.getLong("event_id"));
		return eventImageDTO;
	}

}