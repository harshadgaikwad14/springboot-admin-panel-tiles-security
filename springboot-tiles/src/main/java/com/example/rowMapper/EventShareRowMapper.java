package com.example.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.model.EventShare;

@Component
public class EventShareRowMapper implements RowMapper<EventShare> {

	public EventShare mapRow(ResultSet rs, int rowNum) throws SQLException {
		final EventShare eventShare = new EventShare();
		eventShare.setEventShareId(rs.getLong("eventShareId"));
		eventShare.setEventId(rs.getLong("eventId"));
		eventShare.setClientId(rs.getLong("clientId"));
		eventShare.setDrName(rs.getString("drName"));
		eventShare.setEventName(rs.getString("eventName"));
		eventShare.setEventDate(rs.getDate("eventDate").toString());
		eventShare.setEventShareDate(rs.getDate("eventShareDate").toString());
		eventShare.setEventShareCount(rs.getLong("eventShareCount"));
		
		return eventShare;
	}

}