package com.epam.movietheater.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.movietheater.dao.AirDateDAO;
import com.epam.movietheater.dao.AuditoriumDAO;
import com.epam.movietheater.dao.BookingDAO;
import com.epam.movietheater.dao.EventDAO;
import com.epam.movietheater.dao.UserDAO;
import com.epam.movietheater.dao.impl.AirDateDAOImpl;
import com.epam.movietheater.dao.impl.AuditoriumDAOImpl;
import com.epam.movietheater.dao.impl.BookingDAOImpl;
import com.epam.movietheater.dao.impl.EventDAOImpl;
import com.epam.movietheater.dao.impl.UserDAOImpl;

public class ConfigurationDAO {
	
	@Bean
	public UserDAO userDAO() {
		return new UserDAOImpl();
	}
	
	@Bean
	public EventDAO eventDAO() {
		return new EventDAOImpl();
	}
	
	@Bean
	public BookingDAO bookingDAO() {
		return new BookingDAOImpl();
	}
	
	@Bean
	public AuditoriumDAO auditoriumDAO() {
		return new AuditoriumDAOImpl();
	}
	
	@Bean 
	public AirDateDAO airDateDAO() {
		return new AirDateDAOImpl();
	}
}
