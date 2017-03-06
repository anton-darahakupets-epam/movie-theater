package com.epam.movietheater.dao.impl.statementcreator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.epam.movietheater.domain.User;

public class UserStatementCreator implements PreparedStatementCreator {

	private User user;
	private String sqlQuery;
	
	private UserStatementCreator(User user, String  sqlQuery) {
		this.sqlQuery = sqlQuery;
		this.user = user; 
	}
	
	@Override
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sqlQuery, new String[] {"user_id"});

        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getEmail());
        ps.setDate(3, Date.valueOf(user.getBirthday()));
        ps.setString(4, user.getLastName());
        
        return ps;
	}
	
	public static UserStatementCreator newInstance(User user, String  sqlQuery) {
		return new UserStatementCreator(user, sqlQuery);
	}
}
