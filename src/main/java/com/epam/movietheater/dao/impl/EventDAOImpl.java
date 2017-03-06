package com.epam.movietheater.dao.impl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.movietheater.dao.EventDAO;
import com.epam.movietheater.dao.impl.rowmapper.AirDatesRowMapper;
import com.epam.movietheater.dao.impl.rowmapper.EventRowMapper;
import com.epam.movietheater.dao.impl.rowmapper.UserRowMapper;
import com.epam.movietheater.dao.impl.statementcreator.StatementCreatorFactory;
import com.epam.movietheater.domain.Auditorium;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.User;

public class EventDAOImpl implements EventDAO {
	
	/*static private Set<Event> events;
	
	static {
		events = new HashSet<>();
	}*/
	final static private String SQL_CREATE_EVENT = "INSERT INTO events (event_id,name,base_price,raiting) values (events_event_id_seq.nextval,?,?,?)";
	final static private String SQL_READ_ALL = "SELECT * FROM events";
	final static private String SQL_READ_AIR_DATES = "SELECT air_date FROM air_dates WHERE event_id = ?";
	final static private String SQL_READ_BY_ID = "SELECT * FROM events WHERE event_id = ?";
	final static private String SQL_DELETE_BY_ID = "DELETE FROM events WHERE event_id = ?";
	final static private String SQL_READ_BY_NAME = "SELECT FROM events WHERE name = ?";
	final static private String SQL_ADD_AIR_DATE_TO_EVENT = "INSERT INTO air_dates (event_id, air_date) values (?,?)";
	final static private String SQL_UPDATE_TICKET = "UPDATE events SET name = ?, base_price = ?, raiting = ?  WHERE ticket_id = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Event save(Event object) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update( StatementCreatorFactory.getInstance(object, SQL_CREATE_EVENT), keyHolder);
		object.setId(keyHolder.getKey().longValue());
		return object;
	}
	
	@Override
	public Event update(Event object) {
		jdbcTemplate.update(SQL_UPDATE_TICKET, object.getName(), object.getBasePrice(), object.getRating().toString(), object.getId());
		return object;
	}
	
	@Override
	public void remove(Event object) {
		jdbcTemplate.update(SQL_DELETE_BY_ID, object.getId());
	}

	@Override
	public Event getById(Long id) {
		Event event = null;
		try {
			event =  jdbcTemplate.queryForObject(SQL_READ_BY_ID,
					new Object[]{id}, new EventRowMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			return event;
		}
		return event;
	}

	@Override
	public Collection<Event> getAll() {
		return jdbcTemplate.query(SQL_READ_ALL, new EventRowMapper());
	}
	
	@Override
	public NavigableSet<LocalDateTime> getAirDatesByEventId(Long eventId) {
		return new TreeSet<LocalDateTime>(jdbcTemplate.query(SQL_READ_AIR_DATES, new Object[]{eventId}, new AirDatesRowMapper()));
	}
	
	@Override
	public void addAirDateToEvent(Long eventId, LocalDateTime airDate) {
		jdbcTemplate.update(SQL_ADD_AIR_DATE_TO_EVENT, eventId, Timestamp.valueOf(airDate));
	}
	
	public NavigableMap<LocalDateTime,Auditorium> getAuditoriumsByEventId(Long eventId) {
		return null;
	}
	@Override
	public Event getByName(String name) {
		Event event = null;
		try {
			event =  jdbcTemplate.queryForObject(SQL_READ_BY_NAME,
					new Object[]{name}, new EventRowMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			return event;
		}
		return event;
	}

	@Override
	public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Event> getNextEvents(LocalDateTime to) {
		throw new UnsupportedOperationException();
	}
}
