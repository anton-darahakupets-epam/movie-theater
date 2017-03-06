package com.epam.movietheater.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.movietheater.discount.DiscountStrategy;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.User;
import com.epam.movietheater.service.DiscountService;

public class DiscountServiceImpl implements DiscountService {
	final static private Logger LOGGER = Logger.getLogger(DiscountServiceImpl.class);
	
	@Autowired
    private List<DiscountStrategy> listDiscountStrategies;
	
	@Override
    public byte getMaxDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets, boolean booking) {
		byte discount = 0;
		int size = listDiscountStrategies.size();
		byte[] discounts  = new byte[size];
		int i = 0;
		for (DiscountStrategy discountService: listDiscountStrategies) {
			discounts[i] = discountService.getDiscount(user, event, airDateTime, numberOfTickets, booking).getTotalDiscount();
			i ++;
		}
		Arrays.sort(discounts);
		discount = discounts[size - 1];
		
		LOGGER.info("Total discount = " + discount);
		
		return discount;
	}
}
