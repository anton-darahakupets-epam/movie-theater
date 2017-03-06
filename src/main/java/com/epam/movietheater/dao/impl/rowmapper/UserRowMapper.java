package com.epam.movietheater.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.movietheater.domain.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setBirthday(rs.getDate("birthday").toLocalDate());
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        return user;
	}

}
