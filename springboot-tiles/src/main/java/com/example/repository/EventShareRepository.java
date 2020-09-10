package com.example.repository;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.model.EventShare;
import com.example.rowMapper.EventShareRowMapper;

@Repository
public class EventShareRepository {

	Logger logger = LoggerFactory.getLogger(EventShareRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private EventShareRowMapper eventShareRowMapper;

	public List<EventShare> findAll() {
		final String userQuery = "select es.id as eventShareId,es.event_id as eventId,es.client_id as clientId,es.share_date as eventShareDate,e.event_name as eventName,e.event_date as eventDate,cr.dr_name as drName , COUNT(es.share_date) as eventShareCount from event e , event_share es,client_registration cr where e.id=es.event_id and cr.id=es.client_id group by es.event_id , es.client_id , es.share_date order by es.share_date desc";
		try {
			final List<EventShare> eventShares = namedParameterJdbcTemplate.query(userQuery, eventShareRowMapper);
			logger.info("eventShares : {} ", eventShares);
			return eventShares;
		} catch (Exception e) {

		}

		return Collections.emptyList();
	}

}
