package com.epam.movietheater.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.Ticket;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;


public interface BookingService {

    /**
     * Getting price when buying all supplied seats for particular event
     * 
     * @param event
     *            Event to get base ticket price, vip seats and other
     *            information
     * @param dateTime
     *            Date and time of event air
     * @param user
     *            User that buys ticket could be needed to calculate discount.
     *            Can be <code>null</code>
     * @param seats
     *            Set of seat numbers that user wants to buy
     * @return total price
     */
    double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user,
            @Nonnull Set<Long> seats, boolean booking);

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     * 
     * @param ticketDTOs
     *            Set of tickets
     */
    void bookTickets(@Nonnull Set<Ticket> ticketDTOs, User user, Event event);

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
     * @param ticketDTOs
     */
	void addTickets(Set<Ticket> tickets);

}
