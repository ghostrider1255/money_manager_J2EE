package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.RecurringTransaction;
import com.hibernate.resource.User;
import com.hibernate.service.RecurringTransactionServiceIF;

public class RecurringTransactionServiceImpl implements RecurringTransactionServiceIF{

	@Override
	public void addRecurringTransaction(
			RecurringTransaction recurringTransaction) {
		DaoFactory.getRecurringTransactionInstance().addRecurringTransaction(recurringTransaction);
		
	}

	@Override
	public void delete(RecurringTransaction recurringTransaction) {
		DaoFactory.getRecurringTransactionInstance().delete(recurringTransaction);
		
	}

	@Override
	public List<RecurringTransaction> getRecords(User user) {
		
		return DaoFactory.getRecurringTransactionInstance().getRecords(user);
	}

	@Override
	public RecurringTransaction getRecord(int incomeID) {
		return DaoFactory.getRecurringTransactionInstance().getRecord(incomeID);
	}

	@Override
	public List<RecurringTransaction> getActiveRecords(User user,
			boolean isActive) {
		return DaoFactory.getRecurringTransactionInstance().getActiveRecords(user, isActive);
	}

	@Override
	public void updateRecord(RecurringTransaction recurringTransaction) {
		DaoFactory.getRecurringTransactionInstance().updateRecord(recurringTransaction);
	}

}
