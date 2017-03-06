package com.epam.movietheater.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.movietheater.discount.DiscountStrategy;
import com.epam.movietheater.discount.impl.BirthdayStrategy;
import com.epam.movietheater.discount.impl.EveryTenTicketStrategy;
import com.epam.movietheater.service.DiscountService;
import com.epam.movietheater.service.impl.DiscountServiceImpl;

public class ConfigurationDiscount {
	
	@Bean
	public DiscountStrategy birthDayStrategy() {
		return new BirthdayStrategy();
	}
	
	@Bean
	public DiscountStrategy everyTenStrategy() {
		return new EveryTenTicketStrategy();
	}
	
	@Bean
	public DiscountService discountService() {
		return new DiscountServiceImpl();
	}
}
