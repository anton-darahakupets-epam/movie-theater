package com.epam.movietheater.dao.impl.statementcreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class AirDateStatementCreator implements PreparedStatementCreator {

	private LocalDateTime airDate;
	private String sqlQuery;
	
	private AirDateStatementCreator(LocalDateTime airDate, String  sqlQuery) {
		this.sqlQuery = sqlQuery;
		this.airDate = airDate; 
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sqlQuery, new String[] {"air_date_id"});

        ps.setTimestamp(1, Timestamp.valueOf(airDate));
        
        return ps;
	}
	
	public static AirDateStatementCreator newInstance(LocalDateTime airDate, String  sqlQuery) {
		return new AirDateStatementCreator(airDate, sqlQuery);
	}

}
