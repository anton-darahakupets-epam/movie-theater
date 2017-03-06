package com.epam.movietheater.discount.impl;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.epam.movietheater.discount.DiscountStrategy;
import com.epam.movietheater.domain.Discount;
import com.epam.movietheater.domain.DiscountType;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.User;

public class BirthdayStrategy implements DiscountStrategy {
	
	final static private Logger LOGGER = Logger.getLogger(BirthdayStrategy.class);
	
	final static private byte DEFAULT_DISCOUNT = 0;
	final static private byte BIRTHDAY_DISCOUNT = 5; 
	final static private long DAYS_DISCOUNTS = 5;
	
	/**
	 * If user has Birthday today, then he will get the BIRTHDAY_DISCOUNT during DAYS_DISCOUNTS days
	 */
	@Override
	public Discount getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets, boolean booking) {
		LocalDateTime from = LocalDateTime.now(); 
		LocalDateTime to = from.plusDays(DAYS_DISCOUNTS);
		boolean checkDaysDiscount = (((airDateTime.compareTo(from)) >= 0) && (airDateTime.compareTo(to)) <= 0);
		if (checkDaysDiscount) {
			
			LOGGER.info("User has \"Birthday discount\" = " + BIRTHDAY_DISCOUNT);
			
			return new Discount(BIRTHDAY_DISCOUNT , DiscountType.BIRTHDAY_DISCOUNT, new Integer(1), booking);
		} else {
			return new Discount(DEFAULT_DISCOUNT , DiscountType.BIRTHDAY_DISCOUNT, new Integer(0), booking);
		}
	}
}
