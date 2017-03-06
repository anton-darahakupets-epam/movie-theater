package com.epam.movietheater.dao.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.movietheater.dao.AuditoriumDAO;
import com.epam.movietheater.domain.Auditorium;

public class AuditoriumDAOImpl implements AuditoriumDAO {
	
	@Autowired
	private Set<Auditorium> auditoriums;
	
	public void setAuditoriums(Set<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}
	
	@Override
	public Set<Auditorium> getAll() {
		return auditoriums;
	}

	@Override
	public Auditorium getByName(String name) {
		for (Auditorium auditorium: auditoriums) {
			if (auditorium.getName().equals(name)) {
				return auditorium;
			}
		}
		return null;
	}
}
