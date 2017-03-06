package com.epam.movietheater.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ConfigurationAuditorium.class, ConfigurationDAO.class,
	ConfigurationService.class, ConfigurationDiscount.class, JdbcConfiguration.class})
@ComponentScan(basePackages = "com.epam.movietheater")
@EnableAspectJAutoProxy
public class ApplicationConfiguration {
	
	
}
