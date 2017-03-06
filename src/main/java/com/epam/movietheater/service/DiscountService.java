package com.epam.movietheater.service;

import java.time.LocalDateTime;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.User;


public interface DiscountService {

    /**
     * Getting max discount 
     * 
     * @param user
     *            User that buys tickets. Can be <code>null</code>
     * @param event
     *            Event that tickets are bought for
     * @param airDateTime
     *            The date and time event will be aired
     * @param numberOfTickets
     *            Number of tickets that user buys
     * @param booking it is used by Aspect
     * @return discount value from 0 to 100
     */
    byte getMaxDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets, boolean booking);

}
