package com.epam.movietheater.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.movietheater.dao.EventDAO;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.service.EventService;

public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDAO eventDAO;
	
	public EventServiceImpl(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	public EventServiceImpl() {}
	
	public void setEventDAO(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	@Override
	public Event save(Event object) {
		
		return eventDAO.save(object);
	}

	@Override
	public void remove(Event object) {
		eventDAO.remove(object);
	}

	@Override
	public Event getById(Long id) {
		Event event = eventDAO.getById(id);
		event.setAirDates(eventDAO.getAirDatesByEventId(id));
		
		return event;
	}

	@Override
	public Collection<Event> getAll() {
		return eventDAO.getAll();
	}

	@Override
	public Event getByName(String name) {
		return eventDAO.getByName(name);
	}

	@Override
	public Set<Event> getForDateRange(LocalDate from, LocalDate to) {
		return eventDAO.getForDateRange(from, to);
	}

	@Override
	public Set<Event> getNextEvents(LocalDateTime to) {
		return eventDAO.getNextEvents(to);
	}

	@Override
	public void addAirDateTimeToEvent(Long eventId, LocalDateTime airDate) {
		eventDAO.addAirDateToEvent(eventId, airDate);
		
	}
}
