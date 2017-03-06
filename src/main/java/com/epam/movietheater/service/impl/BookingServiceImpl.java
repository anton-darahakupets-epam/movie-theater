package com.epam.movietheater.service.impl;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.movietheater.dao.BookingDAO;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.EventRating;
import com.epam.movietheater.domain.Ticket;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;
import com.epam.movietheater.service.BookingService;
import com.epam.movietheater.service.DiscountService;

public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private BookingDAO bookingDAO;
	
	public BookingServiceImpl() {
		
	}
	
	public BookingServiceImpl(BookingDAO bookingDAO) {
		this.bookingDAO = bookingDAO;
	}
	
	public void setBookingDAO(BookingDAO bookingDAO) {
		this.bookingDAO = bookingDAO;
	}
	
	final static private double COFFICIENT_FOR_VIP_SEAT = 2.0;
	final static private double COFFICIENT_FOR_HIGH_RATING = 1.2;
	
	@Autowired
	private DiscountService discountService;
	
	@Override
	public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats, boolean booking) {
		double totalPrice = 0;
		double baseEventPrice = event.getBasePrice();
		String eventRating = event.getRating().name();
		Set<Long> vipSeats = event.getAuditoriumWithAirDate(dateTime).getVipSeats();
		int numberOfTickets = seats.size();
		
		if (eventRating.equals(EventRating.HIGH)) {
			baseEventPrice = baseEventPrice * COFFICIENT_FOR_HIGH_RATING;
		}
		
		for (Long seat : seats) {
			if (vipSeats.contains(seat)) {
				totalPrice = totalPrice + (baseEventPrice * COFFICIENT_FOR_VIP_SEAT); 
			} else {
				totalPrice += baseEventPrice;
			}
		}
	
		byte totalDiscount = discountService.getMaxDiscount(user, event, dateTime, numberOfTickets, booking);
		
		totalPrice = totalPrice - (totalPrice * totalDiscount)/100;
		return totalPrice;
	}

	@Override 
	public void addTickets(Set<Ticket> tickets) {
		bookingDAO.createTickets(tickets);
	}
	
	@Override
	public void bookTickets(Set<Ticket> ticketDTOs, User user, Event event) {
		bookingDAO.createTickets(ticketDTOs);
		bookingDAO.attachEventToTickets(ticketDTOs,event);
		bookingDAO.attachUserToTickets(ticketDTOs, user);
	}

	@Override
	public Set<TicketDTO> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
		return bookingDAO.getPurchasedTicketsForEvent(event, dateTime);
	}

}
