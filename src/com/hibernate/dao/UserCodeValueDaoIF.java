package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;

public interface UserCodeValueDaoIF 
{
	public void addUserCodeValue(UserCodeValue userCodeValue);
	
	public void delete(UserCodeValue userCodeValue);
	
	public List<UserCodeValue> getRecords(User user);
	
	public List<UserCodeValue> getRecords(User user,String columnName,String value);
	
	public UserCodeValue getRecord(int userCodeID);
	
	public List<UserCodeValue> getActiveRecords(User user,boolean isActive);
	
	public void updateRecord(UserCodeValue userCodeValue);

}
