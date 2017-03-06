package com.epam.movietheater.dao.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.movietheater.dao.AirDateDAO;
import com.epam.movietheater.dao.impl.statementcreator.StatementCreatorFactory;

public class AirDateDAOImpl implements AirDateDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	final static private String SQL_CREATE_AIR_DATE = "INSERT INTO air_dates (air_date_id, air_date) values (?,?)";
	final static private String SQL_ATTACH_TO_EVENT = "INSERT INTO events_air_dates (event_id, air_date_id) values (?,?)";
	final static private String SQL_READ_ALL_AIR_DATES_BY_EVENT_ID = "SELECT * from events_air_dates LEFT JOIN air_dates ";
			
	@Override
	public Long create(LocalDateTime object) {
		Long airDateId = null;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(StatementCreatorFactory.getInstance(object, SQL_CREATE_AIR_DATE), keyHolder);
		airDateId = keyHolder.getKey().longValue(); 
		return airDateId;
	}

	@Override
	public void attachToEvent(Long eventId, Long airDateId) {
		jdbcTemplate.update(SQL_ATTACH_TO_EVENT, eventId, airDateId);	
	}
}
