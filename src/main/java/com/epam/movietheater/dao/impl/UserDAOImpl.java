package com.epam.movietheater.dao.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.epam.movietheater.dao.UserDAO;
import com.epam.movietheater.dao.impl.rowmapper.UserRowMapper;
import com.epam.movietheater.dao.impl.statementcreator.StatementCreatorFactory;
import com.epam.movietheater.domain.User;

public class UserDAOImpl implements UserDAO {
	
	final static private String SQL_CREATE_USER = "INSERT INTO users (user_id,first_name,email,birthday,last_name) values (users_user_id_seq.nextval,?,?,?,?)";
	final static private String SQL_READ_ALL_USERS = "SELECT user_id,first_name,email,birthday,last_name FROM users";
	final static private String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";
	final static private String SQL_READ_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
	final static private String SQL_READ_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User save(User object) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update( StatementCreatorFactory.getInstance(object, SQL_CREATE_USER), keyHolder);
		object.setId(keyHolder.getKey().longValue());
		return object;
	}

	@Override
	public void remove(User object) {
		jdbcTemplate.update(SQL_DELETE_USER_BY_ID, object.getId());
	}

	@Override
	public User getById(Long id) {
		User user = null;
		try {
			user =  jdbcTemplate.queryForObject(SQL_READ_USER_BY_ID,
					new Object[]{id}, new UserRowMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
		return user;
	}

	@Override
	public Collection<User> getAll() {
		return jdbcTemplate.query(SQL_READ_ALL_USERS, new UserRowMapper());
	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			user =  jdbcTemplate.queryForObject(SQL_READ_USER_BY_EMAIL,
					new Object[]{email}, new UserRowMapper());
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
		return user;
	}
}
