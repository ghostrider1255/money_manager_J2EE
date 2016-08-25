package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.Payee;
import com.hibernate.resource.User;

public interface PayeeDaoIF 
{
	public void addPayee(Payee payee);
	
	public void delete(Payee payee);
	
	public List<Payee> getRecords(User user);
	
	public List<Payee> getRecords(User user,String columnName,String value);
	
	public Payee getRecord(int payeeID);
	
	public List<Payee> getActiveRecords(User user,boolean isActive);
	
	public void updateRecord(Payee payee);
}
