package com.example.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.model.EventImageDTO;
import com.example.rowMapper.EventImageRowMapper;

@Repository
public class EventImageRepository {

	Logger logger = LoggerFactory.getLogger(EventImageRepository.class);

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private EventImageRowMapper eventImageRowMapper;

//	public List<Role> findByUserId(long userId) {
//		final String userQuery = "select r.id,r.name from role r LEFT OUTER JOIN users_roles ur on r.id=ur.role_id where ur.user_id=:userId";
//
//		final SqlParameterSource parameters = new MapSqlParameterSource("userId", userId);
//		try {
//			final List<Role> roles = namedParameterJdbcTemplate.query(userQuery, parameters, roleRowMapper);
//			logger.info("roles : {} ", roles);
//			return roles;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return Collections.emptyList();
//	}

	public EventImageDTO findById(long eventImageId) {
		final String userQuery = "SELECT id, img_url, event_id, modified_at FROM event_img WHERE  id=:eventImageId";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventImageId", eventImageId);
		try {
			final EventImageDTO eventImageDTO = namedParameterJdbcTemplate.queryForObject(userQuery, parameters,
					eventImageRowMapper);
			logger.info("eventImageDTO : {} ", eventImageDTO);
			return eventImageDTO;
		} catch (Exception e) {

		}

		return null;

	}
	
	public EventImageDTO findByEventImageId(long eventId,long eventImageId) {
		final String userQuery = "SELECT id, img_url, event_id, modified_at FROM event_img WHERE  id=:eventImageId and event_id =:eventId";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventImageId", eventImageId).addValue("eventId", eventId);
		try {
			final EventImageDTO eventImageDTO = namedParameterJdbcTemplate.queryForObject(userQuery, parameters,
					eventImageRowMapper);
			logger.info("eventImageDTO : {} ", eventImageDTO);
			return eventImageDTO;
		} catch (Exception e) {

		}

		return null;

	}

	public List<EventImageDTO> findByEventId(long eventId) {
		final String userQuery = "SELECT id, img_url, event_id, modified_at FROM event_img WHERE  event_id=:eventId";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventId", eventId);
		try {
			final List<EventImageDTO> eventImageDTOs = namedParameterJdbcTemplate.query(userQuery, parameters,
					eventImageRowMapper);
			logger.info("eventImageDTOs : {} ", eventImageDTOs);
			return eventImageDTOs;
		} catch (Exception e) {

		}

		return null;

	}
	
	 public int getCountByEventId(long eventId){
		  String sql = "SELECT count(*) as cnt FROM event_img WHERE  event_id=:eventId";
		  SqlParameterSource namedParameters = new MapSqlParameterSource("eventId", eventId);
		  final SqlRowSet sqlRowSet = namedParameterJdbcTemplate.queryForRowSet(sql, namedParameters);
		  int dataCount = 0;
		  while(sqlRowSet.next()) {
			  dataCount = Integer.parseInt(sqlRowSet.getString("cnt"));
			  
			}
		  
		  return dataCount;
	    }

	@Transactional
	public long save(EventImageDTO eventImageDTO) {
		logger.info("eventImageDTO : {} ", eventImageDTO);

		final KeyHolder keyHolder = new GeneratedKeyHolder();
		final String userQuery = "INSERT INTO event_img (img_url, event_id) VALUES (:imagePath, :eventId)";

		final SqlParameterSource parameters = new MapSqlParameterSource("imagePath", eventImageDTO.getImageUrl())
				.addValue("eventId", eventImageDTO.getEventId());

		final int createStatus = namedParameterJdbcTemplate.update(userQuery, parameters, keyHolder);
		logger.info("createStatus : {} ", createStatus);
		return keyHolder.getKey().longValue();
	}

	public int deleteById(long eventImageId) {

		EventImageDTO eventImageDTO = findById(eventImageId);

		if (eventImageDTO == null) {
			return 0;
		}

		final String userQuery = "DELETE FROM event_img WHERE id=:eventImageId";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventImageId", eventImageId);

		final int deleteStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("deleteStatus : {} ", deleteStatus);
		return deleteStatus;
	}
	@Transactional
	public int deleteByEventId(long eventId) {

		List<EventImageDTO> list = findByEventId(eventId);

		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}

		final String userQuery = "DELETE FROM event_img WHERE event_id=:eventId";

		final SqlParameterSource parameters = new MapSqlParameterSource("eventId", eventId);

		final int deleteStatus = namedParameterJdbcTemplate.update(userQuery, parameters);
		logger.info("deleteStatus : {} ", deleteStatus);
		return deleteStatus;
	}
}
