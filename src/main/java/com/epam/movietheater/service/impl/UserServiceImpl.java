package com.epam.movietheater.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.epam.movietheater.dao.UserDAO;
import com.epam.movietheater.domain.User;
import com.epam.movietheater.service.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public UserServiceImpl() {
		
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public User save(User object) {
		return userDAO.save(object);
	}

	@Override
	public void remove(User object) {
		userDAO.remove(object);
	}

	@Override
	public User getById(Long id) {
		return userDAO.getById(id);
	}

	@Override
	public Collection<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public User getUserByEmail(String email) {
		return userDAO.getUserByEmail(email);
	}
}
