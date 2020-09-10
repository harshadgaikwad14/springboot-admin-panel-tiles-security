package com.example.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.EventImageDTO;

@Component
public class EventImageRowMapper implements RowMapper<EventImageDTO> {
	
	@Autowired
	private Environment environment;
	
	


	public EventImageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		final String basePath = environment.getProperty("server.path");
		final EventImageDTO eventImageDTO = new EventImageDTO();
		eventImageDTO.setId(rs.getLong("id"));
		eventImageDTO.setImageUrl(basePath.concat(rs.getString("img_url")));
		eventImageDTO.setEventId(rs.getLong("event_id"));
		return eventImageDTO;
	}

}