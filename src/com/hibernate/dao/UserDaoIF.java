package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.User;

public interface UserDaoIF 
{
	public void addUser(User user);
	
	public void delete(User user);
	
	public List<User> getRecords();
	
	public User getRecord(int userID);
	
	public List<User> getActiveRecords(boolean isActive);
	
	public void updateRecord(User user);
	
	public User validateUser(User user);
	
	public boolean isUserNameAvailable(User user);
}
