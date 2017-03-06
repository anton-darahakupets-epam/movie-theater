package com.epam.movietheater.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.movietheater.service.AuditoriumService;
import com.epam.movietheater.service.BookingService;
import com.epam.movietheater.service.EventService;
import com.epam.movietheater.service.UserService;
import com.epam.movietheater.service.impl.AuditoriumServiceImpl;
import com.epam.movietheater.service.impl.BookingServiceImpl;
import com.epam.movietheater.service.impl.EventServiceImpl;
import com.epam.movietheater.service.impl.UserServiceImpl;

//@Configuration
public class ConfigurationService {
	
	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
	
	@Bean
	public EventService eventService() {
		return new EventServiceImpl();
	}
	
	@Bean
	public BookingService bookingService() {
		return new BookingServiceImpl();
	}
	
	@Bean
	public AuditoriumService auditoriumService() {
		return new AuditoriumServiceImpl();
	}
}