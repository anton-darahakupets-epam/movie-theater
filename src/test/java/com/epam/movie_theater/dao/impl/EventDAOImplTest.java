package com.epam.movie_theater.dao.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

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
import com.epam.movietheater.dao.EventDAO;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.EventRating;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationTestConfiguration.class})
public class EventDAOImplTest extends DBTestCase {
	
	@Autowired
	private EventDAO eventDAO;
	
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
		return new FlatXmlDataSetBuilder().build(this.getClass().getResourceAsStream("/eventDataSet.xml"));
	}
	
	@Test
	public void testSave() {
		Event expectedEvent = new Event();
		expectedEvent.setId(102L);
		expectedEvent.setName("Event102");
		expectedEvent.setBasePrice(15);
		expectedEvent.setRating(EventRating.HIGH);
		
		Event actualEvent = eventDAO.save(expectedEvent);
		
		Collection<Event> actualEvents = eventDAO.getAll();
		
		Assert.assertEquals(actualEvents.size(), 3);
		Assert.assertEquals(actualEvent, expectedEvent);
	}
	
	@Test
	public void testRemove() {
		Event expectedEvent = new Event();
		expectedEvent.setId(100L);
		expectedEvent.setName("asdfsdf");
		expectedEvent.setBasePrice(13);
		expectedEvent.setRating(EventRating.LOW);
		
		eventDAO.remove(expectedEvent);
		
		Event actualEvent = eventDAO.getById(100L);
		
		assertNull(actualEvent);
	}
	
	@Test 
	public void testGetById() {
		
		Event expectedEvent = new Event();
		expectedEvent.setId(100L);
		expectedEvent.setName("asdfsdf");
		expectedEvent.setBasePrice(13);
		expectedEvent.setRating(EventRating.LOW);
		
		Event actualEvent = eventDAO.getById(100L);
		 
		assertEquals(expectedEvent, actualEvent);
	}
	
	@Test
	public void testGetAll() {
		Collection<Event> actualEvents = eventDAO.getAll();
		assertEquals(2, actualEvents.size());
	}

}
