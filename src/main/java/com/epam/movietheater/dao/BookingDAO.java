package com.epam.movietheater.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.Ticket;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;

public interface BookingDAO {

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     * 
     * @param tickets
     *            Set of tickets
     */
    //void bookTickets(@Nonnull Set<TicketDTO> tickets);

    /**
     * Getting all purchased tickets for event on specific air date and time
     * 
     * @param event
     *            Event to get tickets for
     * @param dateTime
     *            Date and time of airing of event
     * @return set of all purchased tickets
     */
    @Nonnull Set<TicketDTO> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);
    
    /**
     * 
     * @param ticket
     */
	void createTickets(Set<Ticket> tickets);
	
	/**
	 * 
	 * @param ticketDTOs
	 */
	void attachUserToTickets(Set<Ticket> tickets, User user);
	
	/**
	 * 
	 * @param ticketDTOs
	 */
	void attachEventToTickets(Set<Ticket> ticketDTOs,  Event event);
	
	/**
	 * 
	 * @return
	 */
	Set<Ticket> getAll();
}
