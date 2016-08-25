package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.User;
import com.hibernate.service.UserServiceIF;

public class UserServiceImpl implements UserServiceIF
{

	@Override
	public void addUser(User user) {
		DaoFactory.getUserInstance().addUser(user);
		
	}

	@Override
	public void delete(User user) {
		DaoFactory.getUserInstance().delete(user);
		
	}

	@Override
	public List<User> getRecords() {
		
		return DaoFactory.getUserInstance().getRecords();
	}

	@Override
	public User getRecord(int userID) {
		
		return DaoFactory.getUserInstance().getRecord(userID);
	}

	@Override
	public List<User> getActiveRecords(boolean isActive) {
		
		return DaoFactory.getUserInstance().getActiveRecords(isActive);
	}

	@Override
	public void updateRecord(User user) {
		DaoFactory.getUserInstance().updateRecord(user);
		
	}

	@Override
	public User validateUser(User user) {
		return DaoFactory.getUserInstance().validateUser(user);
	}

	@Override
	public boolean isUserNameAvailable(User user) {
		return DaoFactory.getUserInstance().isUserNameAvailable(user);
	}

}
