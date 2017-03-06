package com.epam.movie_theater.configuration;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.epam.movietheater.configuration.ConfigurationAuditorium;
import com.epam.movietheater.configuration.ConfigurationDAO;
import com.epam.movietheater.configuration.JdbcConfiguration;

@Configuration
@Import({ConfigurationDAO.class, JdbcConfiguration.class, ConfigurationAuditorium.class})
public class ApplicationTestConfiguration {
	
	@Bean
	public IDatabaseTester tester(DataSource dataSource) {
		return new DataSourceDatabaseTester(dataSource);
	}
}
