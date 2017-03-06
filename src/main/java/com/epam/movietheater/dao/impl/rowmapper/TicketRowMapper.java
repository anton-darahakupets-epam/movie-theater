package com.epam.movietheater.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.movietheater.domain.Ticket;

public class TicketRowMapper implements RowMapper<Ticket>{

	@Override
	public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setId(rs.getLong("ticket_id"));
		ticket.setDateTime(rs.getTimestamp("air_date").toLocalDateTime());
		ticket.setSeat(rs.getInt("seat"));
		return ticket;
	}
}
