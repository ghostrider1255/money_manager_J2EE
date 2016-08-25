package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.RecurringTransaction;
import com.hibernate.resource.User;

public interface RecurringTransactionDaoIF 
{
    public void addRecurringTransaction(RecurringTransaction recurringTransaction);
	
	public void delete(RecurringTransaction recurringTransaction);
	
	public List<RecurringTransaction> getRecords(User user);
	
	public RecurringTransaction getRecord(int incomeID);
	
	public List<RecurringTransaction> getActiveRecords(User user,boolean isActive);
	
	public void updateRecord(RecurringTransaction recurringTransaction);
}
