package com.epam.movietheater.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.EventRating;

public class EventRowMapper implements RowMapper<Event>{

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Event event = new Event();
		 event.setId(rs.getLong("event_id"));
		 event.setName(rs.getString("name"));
		 event.setBasePrice(rs.getInt("base_price"));
		 event.setRating(EventRating.valueOf(rs.getString("raiting")));
		 return event;
	}	
	

}
