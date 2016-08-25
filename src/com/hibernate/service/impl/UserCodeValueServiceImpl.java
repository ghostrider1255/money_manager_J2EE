package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.service.UserCodeValueServiceIF;

public class UserCodeValueServiceImpl implements UserCodeValueServiceIF
{

	@Override
	public void addUserCodeValue(UserCodeValue userCodeValue) {
		DaoFactory.getUserCodeValueInstance().addUserCodeValue(userCodeValue);
		
	}

	@Override
	public void delete(UserCodeValue userCodeValue) {
		DaoFactory.getUserCodeValueInstance().delete(userCodeValue);
		
	}

	@Override
	public List<UserCodeValue> getRecords(User user) {
		return DaoFactory.getUserCodeValueInstance().getRecords(user);
	}

	@Override
	public UserCodeValue getRecord(int userCodeID) {
		
		return DaoFactory.getUserCodeValueInstance().getRecord(userCodeID);
	}

	@Override
	public List<UserCodeValue> getActiveRecords(User user,boolean isActive) {
		return DaoFactory.getUserCodeValueInstance().getActiveRecords(user,isActive);
	}

	@Override
	public void updateRecord(UserCodeValue userCodeValue) {
		DaoFactory.getUserCodeValueInstance().updateRecord(userCodeValue);
	}

	@Override
	public List<UserCodeValue> getRecords(User user,String columnName, String value) {
		return DaoFactory.getUserCodeValueInstance().getRecords(user,columnName, value);
	}

}
