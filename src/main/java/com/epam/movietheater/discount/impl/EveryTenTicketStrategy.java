package com.epam.movietheater.discount.impl;

import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import com.epam.movietheater.discount.DiscountStrategy;
import com.epam.movietheater.domain.Discount;
import com.epam.movietheater.domain.DiscountType;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.User;

public class EveryTenTicketStrategy implements DiscountStrategy {
	final static private Logger LOGGER = Logger.getLogger(EveryTenTicketStrategy.class); 
	
	final static private byte DEFAULT_DISCOUNT = 0;
	final static private double EVERY_TEN_TICKET_DISCOUNT = 0.5; 
	final static private int NUMBER_TICKET_DISCOUNT = 10;
	
	/**
	 * Count discount for particular number of tickets 
	 * Return total discount for total price to this number of tickets   
	 * Every 10th ticket has 50% discount
	 */
	@Override
	public Discount getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets, boolean booking) {
		
		Discount discount = new Discount(DEFAULT_DISCOUNT, DiscountType.EVERY_TEN_TICKET_DISCOUNT, new Integer(0), booking);
		double price = 0.0;
		double eventBasePrice = event.getBasePrice();
		double totalPrice = eventBasePrice * numberOfTickets;
		byte totalDiscount = DEFAULT_DISCOUNT;
		
		if (user == null) {
			for (int j = 1; j <= numberOfTickets; j++) {
				if (j % NUMBER_TICKET_DISCOUNT == 0) {
					price = price + EVERY_TEN_TICKET_DISCOUNT * eventBasePrice;
					discount.setCounter(discount.getCounter() + 1);
				} else {
					price += eventBasePrice;
				}
			}
			
			totalDiscount = (byte)((1 - (double)(price/totalPrice))*100);
			
			LOGGER.info("Total \"EveryTenTicket \" discount" + (totalDiscount) + "%");
			
			discount.setTotalDiscount(totalDiscount);
			return discount;
		} else {
			int userBookedTickets = user.getTickets().size();
			LOGGER.info("User " + user.getFirstName() + " already has purchased tickets = " + userBookedTickets);
			for (int j = 1; j <= numberOfTickets; j++) {
				if ((userBookedTickets + j) % NUMBER_TICKET_DISCOUNT == 0) {
					price = price + EVERY_TEN_TICKET_DISCOUNT * eventBasePrice;
					discount.setCounter(discount.getCounter() + 1);
					LOGGER.info("User " + user.getFirstName() + " purchased " +  (userBookedTickets + j) + "th" + " ticket!");
				} else {
					price += eventBasePrice;
				}
			}
			
			totalDiscount = (byte)((1 - (double)(price/totalPrice))*100);
			
			LOGGER.info("Total \"EveryTenTicket \" discount = " + (totalDiscount) + "%");
			
			discount.setTotalDiscount(totalDiscount);
			return discount;
		}
	}
}
