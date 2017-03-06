package com.epam.movietheater.ui.console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.experimental.max.CouldNotReadCoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.movietheater.configuration.ApplicationConfiguration;
import com.epam.movietheater.domain.Auditorium;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.EventRating;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;
import com.epam.movietheater.service.AuditoriumService;
import com.epam.movietheater.service.BookingService;
import com.epam.movietheater.service.EventService;
import com.epam.movietheater.service.UserService;
import com.epam.movietheater.ui.console.state.MainState;

import quoters.TerminatorQuoter;


public class SpringHometaskConsoleUI {

    private static ApplicationContext context;

    public static void main(String[] args) {
        SpringHometaskConsoleUI ui = new SpringHometaskConsoleUI();
        ui.initContext();
        context.getBean(TerminatorQuoter.class).sayQuoter();
        context.getBean(TerminatorQuoter.class).sayQuoter();
        
        //ui.run();
    }

    private void initContext() {
    	context = new ClassPathXmlApplicationContext("springContext.xml");   
    	//context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    	
    }

    private void run() {
        System.out.println("Welcome to movie theater console service");
        
        fillInitialData();

        MainState state = new MainState(context);

        state.run();

        System.out.println("Exiting.. Thank you.");
    }

    private void fillInitialData() {
        UserService userService = context.getBean(UserService.class);
        EventService eventService = context.getBean(EventService.class);
        AuditoriumService auditoriumService = context.getBean(AuditoriumService.class);
        BookingService bookingService = context.getBean(BookingService.class);
        Auditorium auditorium = null ;
        
        if (auditoriumService.getAll().iterator().hasNext()) {
        	auditorium = auditoriumService.getAll().iterator().next();
        } else {
        	throw new IllegalStateException("Failed to fill initial data - no auditoriums returned from AuditoriumService");
        }
        if (auditorium.getNumberOfSeats() <= 0) {
            throw new IllegalStateException("Failed to fill initial data - no seats in the auditorium " + auditorium.getName());
        }
        
        User user = new User();
        user.setId(1L);
        user.setEmail("my@email.com");
        user.setFirstName("Foo");
        user.setLastName("Bar");
        user.setBirthday(LocalDate.of(1993, 12, 2));
        
        //user = userService.save(user);
        
        Event event = new Event();
        event.setId(1L);
        event.setName("Grand concert");
        event.setRating(EventRating.MID);
        event.setBasePrice(10);
        LocalDateTime airDate = LocalDateTime.of(2020, 6, 15, 19, 30);
        event.addAirDateTime(airDate, auditorium);
        
        event = eventService.save(event);
        
        TicketDTO ticket1 = new TicketDTO(user, event, airDate, 1);
       // bookingService.bookTickets(Collections.singleton(ticket1));
        
        if (auditorium.getNumberOfSeats() > 1) {
            User userNotRegistered = new User();
            userNotRegistered.setEmail("somebody@a.b");
            userNotRegistered.setFirstName("A");
            userNotRegistered.setLastName("Somebody");
            TicketDTO ticket2 = new TicketDTO(userNotRegistered, event, airDate, 2);
          //  bookingService.bookTickets(Collections.singleton(ticket2));
        }
    }
}
