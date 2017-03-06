package com.epam.movietheater.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.movietheater.dao.BookingDAO;
import com.epam.movietheater.dao.impl.rowmapper.TicketDTORowMapper;
import com.epam.movietheater.dao.impl.rowmapper.TicketRowMapper;
import com.epam.movietheater.dao.impl.statementcreator.StatementCreatorFactory;
import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.Ticket;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;

public class BookingDAOImpl implements BookingDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	final static private String SQL_ADD_TICKETS = "INSERT INTO tickets (ticket_id, air_date, seat) values (tickets_ticket_id_seq.nextval,?,?)";
	final static private String SQL_READ_PURCHASED_TICKETS_BY_EVENT_ID = "SELECT t.ticket_id, t.air_date, t.seat, u.user_id, u.first_name, u.last_name, u.email, u.birthday, e.event_id, e.name,e.base_price, e.raiting"
			+ " FROM tickets t \n" + " LEFT JOIN USERS_TICKETS u2 ON u2.TICKET_ID = t.TICKET_ID \n"
			+ " LEFT JOIN users u ON u.USER_ID = u.USER_ID \n"
			+ " LEFT JOIN EVENTS_TICKETS e2 ON e2.TICKET_ID = t.TICKET_ID \n"
			+ " LEFT JOIN EVENTS e ON e.EVENT_ID = e2.EVENT_ID \n"
			+ "LEFT JOIN events_air_dates e3 ON e.EVENT_ID = e3.EVENT_ID \n "
			+ "LEFT JOIN AIR_DATES a ON a.AIR_DATE_ID = e3.AIR_DATE_ID \n "
			+ " WHERE e.event_id = ? AND t.air_date = ?";

	final static private String SQL_ADD_USER_TO_EVENT = "INSERT INTO USERS_TICKETS (user_id, ticket_id) values (?,?)";
	final static private String SQL_ADD_EVENT_TO_TICKET = "INSERT INTO EVENTS_TICKETS (event_id, ticket_id) values (?,?)";
	final static private String SQL_READ_ALL = "SELECT t.ticket_id, t.air_date, t.seat, u.user_id, u.first_name, u.last_name, u.email, u.birthday, e.event_id, e.name,e.base_price, e.raiting"
			+ " FROM tickets t \n" + " LEFT JOIN USERS_TICKETS u2 ON u2.TICKET_ID = t.TICKET_ID \n"
			+ " LEFT JOIN users u ON u.USER_ID = u.USER_ID \n"
			+ " LEFT JOIN EVENTS_TICKETS e2 ON e2.TICKET_ID = t.TICKET_ID \n"
			+ " LEFT JOIN EVENTS e ON e.EVENT_ID = e2.EVENT_ID \n"
			+ "LEFT JOIN events_air_dates e3 ON e.EVENT_ID = e3.EVENT_ID \n "
			+ "LEFT JOIN AIR_DATES a ON a.AIR_DATE_ID = e3.AIR_DATE_ID \n ";
	
	@Override
	public void createTickets(Set<Ticket> tickets) {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (Ticket ticketDTO : tickets) {
			Object[] values = new Object[] { Timestamp.valueOf(ticketDTO.getDateTime()), ticketDTO.getSeat() };
			batch.add(values);
		}
		jdbcTemplate.batchUpdate(SQL_ADD_TICKETS, batch);
	}

	@Override
	public void attachUserToTickets(Set<Ticket> tickets, User user) {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (Ticket ticket : tickets) {
			Object[] values = new Object[] { user.getId(), ticket.getId() };
			batch.add(values);
		}
		jdbcTemplate.batchUpdate(SQL_ADD_USER_TO_EVENT, batch);
	}

	@Override
	public void attachEventToTickets(Set<Ticket> tickets, Event event) {
		List<Object[]> batch = new ArrayList<Object[]>();
		for (Ticket ticket : tickets) {
			Object[] values = new Object[] { event.getId(), ticket.getId() };
			batch.add(values);
		}
		jdbcTemplate.batchUpdate(SQL_ADD_EVENT_TO_TICKET, batch);
	}

	@Override
	public Set<TicketDTO> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
		List<TicketDTO> purchasedTickets = jdbcTemplate.query(SQL_READ_PURCHASED_TICKETS_BY_EVENT_ID,
				new Object[] { event.getId(), Timestamp.valueOf(dateTime) }, new TicketDTORowMapper());
		Set<TicketDTO> ticketDTOs = new TreeSet<>(purchasedTickets);
		return ticketDTOs;
	}

	@Override
	public Set<Ticket> getAll() {
		List<Ticket> purchasedTickets = jdbcTemplate.query(SQL_READ_ALL, new TicketRowMapper());
		Set<Ticket> tickets = new TreeSet<>(purchasedTickets);
		return tickets;
	}

}
