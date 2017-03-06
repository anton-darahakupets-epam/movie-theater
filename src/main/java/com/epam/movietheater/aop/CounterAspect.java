package com.epam.movietheater.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.TicketDTO;

@Aspect
@Component
public class CounterAspect {
	static final private Logger LOGGER = Logger.getLogger(CounterAspect.class);
	
	private Map<Event, Integer> counterGetByName = new HashMap<>();
	
	private Map<Event, Integer> counterGetTicketsPrice = new HashMap<>();
	
	private Map<Event, Integer> counterBookTickets = new HashMap<>();
	
	public Map<Event, Integer> getCounterBookTickets() {
		return counterBookTickets;
	}
	
	public Map<Event, Integer> getCounterGetByName() {
		return counterGetByName;
	}
	
	public Map<Event, Integer> getCounterGetTicketsPrice() {
		return counterGetTicketsPrice;
	}
	
	@Pointcut(value = "execution(* com.epam.movietheater.service.EventService.getByName(..))")
	public void eventServiceGetByNameMethod() {}
	
	@AfterReturning(pointcut = "eventServiceGetByNameMethod()", returning = "event")
	protected void countEventAccessedByName(Event event) {
		if (event != null) {
			if (counterGetByName.containsKey(event)) {
				counterGetByName.put(event, counterGetByName.get(event) + 1);
			} else {
				counterGetByName.put(event, new Integer(1));
			}
			LOGGER.info("Event" + " " + "\"" + event.getName() + "\"" + " " + 
						"accessed by name "  + counterGetByName.get(event) + " " + "times");
		} else {
			LOGGER.info("There is not specified event!");
		}
	}
	
	@Pointcut(value = "execution(* com.epam.movietheater.service.BookingService.getTicketsPrice(..))")
	public void bookingServiceGetTicketPricesMethod() {}
	
	@AfterReturning(pointcut = "bookingServiceGetTicketPricesMethod() && args(event,..)")
	protected void countGetTicketsPrices(Event event) {
		
		if (counterGetTicketsPrice.containsKey(event)) {
			counterGetTicketsPrice.put(event, counterGetTicketsPrice.get(event) + 1);
		} else {
			counterGetTicketsPrice.put(event, new Integer(1));
		}
		
		LOGGER.info("Tickets price of the" + " " + "\"" + event.getName() + "\"" + " " + 
					"event is accessed" + " "  + counterGetTicketsPrice.get(event) + " " +"times");
	}
	
	@Pointcut(value = "execution(* com.epam.movietheater.service.BookingService.bookTickets(..))")
	public void bookingServiceBooTicketsMethod() {}
	
	@AfterReturning(pointcut = "bookingServiceBooTicketsMethod() && args(tickets)")
	public void countBookTicketsMethod(Set<TicketDTO> ticketDTOs) {
		Event event = ticketDTOs.iterator().next().getEvent();
		
		if (counterBookTickets.containsKey(event)) {
			counterBookTickets.put(event, counterBookTickets.get(event) + 1);
		} else {
			counterBookTickets.put(event, new Integer(1));
		}
		
		LOGGER.info("Tickets is booked to" + " " + "\"" + event.getName() + "\"" + " " + " " + 
					"event" + "\"" + counterBookTickets.get(event) + " " + "times");
	}
}
