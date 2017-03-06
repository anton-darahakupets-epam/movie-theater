package com.epam.movietheater.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.EventRating;
import com.epam.movietheater.domain.TicketDTO;
import com.epam.movietheater.domain.User;

public class TicketDTORowMapper implements RowMapper<TicketDTO> {

	@Override
	public TicketDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketDTO ticketDTO = new TicketDTO();
		ticketDTO.setId(rs.getLong("ticket_id"));
		ticketDTO.setDateTime(rs.getTimestamp("air_date").toLocalDateTime());
		ticketDTO.setSeat(rs.getInt("seat"));
		
		User user = new User();
		user.setId(rs.getLong("user_id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setEmail(rs.getString("email"));
		user.setBirthday(rs.getDate("birthday").toLocalDate());
		
		Event event = new Event();
		event.setId(rs.getLong("event_id"));
		event.setName(rs.getString("name"));
		event.setBasePrice(rs.getInt("base_price"));
		event.setRating(EventRating.valueOf(rs.getString("raiting")));
		
		ticketDTO.setEvent(event);
		ticketDTO.setUser(user);
		
		return ticketDTO;
	}

}
