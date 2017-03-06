package com.epam.movietheater.dao.impl.statementcreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.epam.movietheater.domain.Event;

public class EventStatementCreator implements PreparedStatementCreator {
	
	private Event event;
	private String sqlQuery;
	
	private EventStatementCreator(Event event, String  sqlQuery) {
		this.sqlQuery = sqlQuery;
		this.event = event; 
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sqlQuery, new String[] {"event_id"});

        ps.setString(1, event.getName());
        ps.setDouble(2, event.getBasePrice());
        ps.setString(3, event.getRating().toString());
        
        return ps;
	}
	
	public static EventStatementCreator newInstance(Event event, String  sqlQuery) {
		return new EventStatementCreator(event, sqlQuery);
	}

}
