package com.example.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.model.EventDTO;
import com.example.model.EventImageDTO;
import com.example.model.Role;
import com.example.rowMapper.EventRowMapper;

@Repository
public class EventRepository {

	Logger logger = LoggerFactory.getLogger(EventRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private EventRowMapper eventRowMapper;

	@Autowired
	private EventImageRepository eventImageRepository;
	
	
	public List<EventDTO> findAll() {
		final String userQuery = "SELECT id, event_name, event_date, modified_at FROM event order by modified_at desc";

		// final SqlParameterSource parameters = new MapSqlParameterSource("userId",
		// userId);
		try {
			final List<EventDTO> eventDTOs = namedParameterJdbcTemplate.query(userQuery, eventRowMapper);

			return eventDTOs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public List<EventDTO> findAllByDateRange(String fromDate, String toDate) {
		final String userQuery = "SELECT id, event_name, event_date, modified_at FROM event WHERE event_date>=:fromDate and event_date<=:toDate";

		final SqlParameterSource parameters = new MapSqlParameterSource("fromDate", fromDate).addValue("toDate",
				toDate);
		try {
			final List<EventDTO> eventDTOs = namedParameterJdbcTemplate.query(userQuery, parameters, eventRowMapper);

			if (!CollectionUtils.isEmpty(eventDTOs)) {
				final List<EventDTO> updatedList = new ArrayList<>();
				for (EventDTO eventDTO : eventDTOs) {

					final List<EventImageDTO> list = eventImageRepository.findByEventId(eventDTO.getId());
					if (!CollectionUtils.isEmpty(list)) {
						eventDTO.setImages(list);
						updatedList.add(eventDTO);
					}
				}
				return updatedList;
			}

			return eventDTOs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	public EventDTO findById(long eventId) {
		final String userQuery = "SELECT id, event_name, event_date, modified_at FROM event WHERE id=:eventId";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventId", eventId);
		try {
			final EventDTO eventDTO = namedParameterJdbcTemplate.queryForObject(userQuery, parameters, eventRowMapper);
			logger.info("eventDTO : {} ", eventDTO);

			List<EventImageDTO> list = eventImageRepository.findByEventId(eventId);
			eventDTO.setImages(list);

			return eventDTO;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;

	}

	public EventDTO findByNameAndDate(String eventName, String eventDate) {
		final String userQuery = "SELECT id, event_name, event_date, modified_at FROM event WHERE event_name=:eventName AND event_date=:eventDate";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventName", eventName).addValue("eventDate",
				eventDate);

		try {

			final EventDTO eventDTO = namedParameterJdbcTemplate.queryForObject(userQuery, parameters, eventRowMapper);
			logger.info("eventDTO : {} ", eventDTO);
			return eventDTO;
		} catch (Exception e) {

		}

		return null;
	}

	@Transactional
	public long save(EventDTO eventDTO) {
		logger.info("eventDTO : {} ", eventDTO);
		/*
		 * final EventDTO exEventDTO = findByNameAndDate(eventDTO.getName(),
		 * eventDTO.getEventDate()); logger.info("exEventDTO : {} ",exEventDTO); if
		 * (exEventDTO != null) { return exEventDTO.getId(); }
		 */

		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final String userQuery = "INSERT INTO event (event_name, event_date) VALUES (:eventName, :eventDate)";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventName", eventDTO.getName())
				.addValue("eventDate", eventDTO.getEventDate());

		final int createStatus = namedParameterJdbcTemplate.update(userQuery, parameters, keyHolder);
		logger.info("createStatus : {} ", createStatus);
		long eventId = keyHolder.getKey().longValue();

		for (EventImageDTO eventImageDTO : eventDTO.getImages()) {
			eventImageDTO.setEventId(eventId);
			eventImageRepository.save(eventImageDTO);
		}

		return eventId;

	}

	public int update(EventDTO eventDTO) {

		EventDTO exEventDTO = findById(eventDTO.getId());
		final String userQuery = "UPDATE event SET event_date=:eventDate ,event_name=:eventName WHERE  id=:id";

		if (StringUtils.isEmpty(eventDTO.getName())) {
			eventDTO.setName(exEventDTO.getName());
		}

		if (StringUtils.isEmpty(eventDTO.getEventDate())) {
			eventDTO.setEventDate(eventDTO.getEventDate());
		}

		final SqlParameterSource parameters = new MapSqlParameterSource("id", eventDTO.getId())
				.addValue("eventDate", eventDTO.getEventDate()).addValue("eventName", eventDTO.getName());

		final int updateStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("updateStatus : {} ", updateStatus);

		if (!CollectionUtils.isEmpty(eventDTO.getImages())) {
			for (EventImageDTO eventImageDTO : eventDTO.getImages()) {
				eventImageDTO.setEventId(eventDTO.getId());
				eventImageRepository.save(eventImageDTO);
			}
		}

		return updateStatus;
	}

	@Transactional
	public int deleteById(long eventId) {

		EventDTO eventDTO = findById(eventId);

		if (eventDTO == null) {
			return 0;
		}

		/*
		 * int count= eventImageRepository.getCountByEventId(eventId);
		 * 
		 * if(count>0) { logger.info("count : {} ", count); return -1; }
		 */

		eventImageRepository.deleteByEventId(eventId);

		final String userQuery = "DELETE FROM event WHERE id=:eventId";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventId", eventId);

		final int deleteStatus = namedParameterJdbcTemplate.update(userQuery, parameters);

		logger.info("deleteStatus : {} ", deleteStatus);
		return deleteStatus;
	}

	public int deleteByEventImageId(long eventId, long eventImageId) {

		EventImageDTO eventImageDTO = eventImageRepository.findByEventImageId(eventId, eventImageId);

		if (eventImageDTO == null) {
			return -1;
		}

		final int deleteStatus = eventImageRepository.deleteById(eventImageDTO.getId());

		logger.info("deleteStatus : {} ", deleteStatus);
		return deleteStatus;
	}
}
