package com.epam.movietheater.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.epam.movietheater.domain.Event;

public interface EventService extends AbstractDomainObjectService<Event> {

    /**
     * Finding event by name
     * 
     * @param name
     *            Name of the event
     * @return found event or <code>null</code>
     */
    @Nullable Event getByName(@Nonnull String name);

    /**
     * Finding all events that air on specified date range
     * 
     * @param from Start date
     * 
     * @param to End date inclusive
     * 
     * @return Set of events
     */
     @Nonnull Set<Event> getForDateRange(@Nonnull LocalDate from,  @Nonnull LocalDate to);

     /**
      * Return events from 'now' till the the specified date time
      *  
      * @param to End date time inclusive
      * 
      * @return Set of events
      */
     @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to);
     
     /**
      * 
      * @param eventId
      * @param airDate
      */
     void addAirDateTimeToEvent(Long eventId, LocalDateTime airDate);

}
