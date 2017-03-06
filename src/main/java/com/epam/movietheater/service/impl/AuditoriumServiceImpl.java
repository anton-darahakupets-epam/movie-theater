package com.epam.movietheater.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.movietheater.dao.AuditoriumDAO;
import com.epam.movietheater.domain.Auditorium;
import com.epam.movietheater.service.AuditoriumService;

public class AuditoriumServiceImpl implements AuditoriumService {
	
	@Autowired
	private AuditoriumDAO auditoriumDAO;
	
	public AuditoriumServiceImpl(AuditoriumDAO auditoriumDAO) {
		this.auditoriumDAO = auditoriumDAO;
	}
	
	public AuditoriumServiceImpl() {}
	
	public void setAuditoriumDAO(AuditoriumDAO auditoriumDAO) {
		this.auditoriumDAO = auditoriumDAO;
	}
	
	@Override
	public Set<Auditorium> getAll() {
		return auditoriumDAO.getAll();
	}

	@Override
	public Auditorium getByName(String name) {
		return auditoriumDAO.getByName(name);
	}
}
