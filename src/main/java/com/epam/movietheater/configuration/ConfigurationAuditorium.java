package com.epam.movietheater.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.epam.movietheater.domain.Auditorium;

@PropertySource("classpath:auditoriums.properties")
public class ConfigurationAuditorium {
	
	@Bean
	@Scope(value = "prototype")
	public Auditorium red(@Value("${red.name}") String name,
						  @Value("${red.numberOfSeats}") int numberOfSeats, 
						  @Value("${red.vipSeats}") String vipSeats) {
		return new Auditorium(name, numberOfSeats, vipSeats);
	}
	
	@Bean
	@Scope(value = "prototype")
	public Auditorium green(@Value("${green.name}") String name,
						    @Value("${green.numberOfSeats}") int numberOfSeats, 
						    @Value("${green.vipSeats}") String vipSeats) {
		return new Auditorium(name, numberOfSeats, vipSeats);
	}
	
	@Bean
	@Scope(value = "prototype")
	public Auditorium white(@Value("${white.name}") String name,
							@Value("${white.numberOfSeats}") int numberOfSeats, 
							@Value("${white.vipSeats}") String vipSeats) {
		return new Auditorium(name, numberOfSeats, vipSeats);
	}
	
	@Bean
	@Scope(value = "prototype")
	public Auditorium black(@Value("${black.name}") String name,
							@Value("${black.numberOfSeats}") int numberOfSeats, 
							@Value("${black.vipSeats}") String vipSeats) {
		return new Auditorium(name, numberOfSeats, vipSeats);
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}
