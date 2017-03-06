package com.epam.movietheater.dao.impl.statementcreator;

import java.time.LocalDateTime;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.epam.movietheater.domain.Event;
import com.epam.movietheater.domain.User;

public class StatementCreatorFactory {
	
	public static PreparedStatementCreator getInstance(Object object, String sqlQuery) {
		
		if (object instanceof Event) {
			return EventStatementCreator.newInstance((Event) object, sqlQuery);
			
		} else if (object instanceof User) {
				return UserStatementCreator.newInstance((User) object, sqlQuery);
		} else if (object instanceof LocalDateTime) {
			return AirDateStatementCreator.newInstance((LocalDateTime) object, sqlQuery);
		}
		return null;
	}

}
