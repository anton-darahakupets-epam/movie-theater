package com.epam.movie_theater.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.formula.functions.Even;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.movie_theater.configuration.ApplicationTestConfiguration;
import com.epam.movietheater.dao.BookingDAO;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.EventRating;
import com.epam.movietheater.domain.Ticket;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTestConfiguration.class})
public class BookingDAOImplTest extends DBTestCase{
	
	@Autowired
	private BookingDAO bookingDAO;
	
	@Autowired
	private IDatabaseTester tester;
	
	@Before
	public void setUp() throws Exception {
		IDataSet dataSet = getDataSet();
		tester.setDataSet(dataSet);
		tester.onSetup();
	}
	
	@After
	public void tearDown() throws Exception {
		tester.setTearDownOperation(getTearDownOperation());
		tester.onTearDown();
	}
		
	@Override
	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/bookingDateSet.xml"));
	}
	
	/*@Test 
	public void testBookTickets() {
		LocalDateTime dateTime = LocalDateTime.of(2013, 12, 31, 9, 17, 17);
		
		Event event = new Event();
		event.setId(100L);
		event.setName("asdfsdf");
		event.setBasePrice(13);
		event.setRating(EventRating.LOW);
		
		User user  = new User();
		user.setId(100L);
		user.setEmail("sdaf");
		user.setFirstName("sport");
		user.setLastName("asdv");
		user.setBirthday(LocalDate.of(2013, 12, 31));
		
		Set<TicketDTO> expectedTickets = new TreeSet<TicketDTO>();
		expectedTickets.add(new TicketDTO(user, event,dateTime, 16));
		expectedTickets.add(new TicketDTO(user, event,dateTime, 17));
		
		
		
		bookingDAO.bookTickets(expectedTickets);
		
		Set<TicketDTO> actualTickets = bookingDAO.getPurchasedTicketsForEvent(event, dateTime);
		
		Assert.assertEquals(actualTickets.size() - expectedTickets.size() , expectedTickets.size());		
	}*/
	
	@Test 
	public void testCreateTickets() {
		NavigableSet<Ticket> expectedTickets = new TreeSet<>();
		Ticket ticket = new Ticket();
		ticket.setSeat(17L);
		ticket.setDateTime(LocalDateTime.of(2013, 12, 31, 9, 17, 17));
		
		expectedTickets.add(ticket);
		
		ticket = new Ticket();
		ticket.setSeat(16L);
		ticket.setDateTime(LocalDateTime.of(2013, 12, 31, 9, 17, 17));
		
		expectedTickets.add(ticket);
		
		bookingDAO.createTickets(expectedTickets);
		
		Set<Ticket> actualTickets = bookingDAO.getAll();
		
		assertEquals(expectedTickets.size() + 2, actualTickets.size());
		
	}
	
	@Test
	public void testGetPurchasedTickets() {
		
		LocalDateTime dateTime = LocalDateTime.of(2013, 12, 31, 9, 17, 17);
		Event event = new Event();
		event.setId(100L);
		event.setName("asdfsdf");
		event.setBasePrice(13);
		event.setRating(EventRating.LOW);
		
		User user  = new User();
		user.setId(100L);
		user.setEmail("sdaf");
		user.setFirstName("sport");
		user.setLastName("asdv");
		user.setBirthday(LocalDate.of(2013, 12, 31));
		
		Set<TicketDTO> expectedTickets = new TreeSet<TicketDTO>();
		
		expectedTickets.add(new TicketDTO(user, event, dateTime, 14));
		expectedTickets.add(new TicketDTO(user, event, dateTime, 15));
		
		Set<TicketDTO> actualTickets = bookingDAO.getPurchasedTicketsForEvent(event, dateTime);
		
		Assert.assertEquals(expectedTickets.size(), actualTickets.size());
		
		Assert.assertEquals(expectedTickets, actualTickets);
		
	}
	
	@Test 
	public void testAttachUserToTickets() {
		User user  = new User();
		user.setId(100L);
		user.setEmail("sdaf");
		user.setFirstName("sport");
		user.setLastName("asdv");
		
		//bookingDAO.attachUserToTickets(ticketDTOs);
		
		
	}
}
