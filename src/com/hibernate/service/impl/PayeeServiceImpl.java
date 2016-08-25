package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.Payee;
import com.hibernate.resource.User;
import com.hibernate.service.PayeeServiceIF;

public class PayeeServiceImpl implements PayeeServiceIF
{

	@Override
	public void addPayee(Payee payee) {
		DaoFactory.getPayeeInstance().addPayee(payee);
		
	}

	@Override
	public void delete(Payee payee) {
		DaoFactory.getPayeeInstance().delete(payee);
		
	}

	@Override
	public List<Payee> getRecords(User user) {
		
		return DaoFactory.getPayeeInstance().getRecords(user);
	}

	@Override
	public List<Payee> getRecords(User user, String columnName, String value) {
		
		return DaoFactory.getPayeeInstance().getRecords(user, columnName, value);
	}

	@Override
	public Payee getRecord(int payeeID) {
		
		return DaoFactory.getPayeeInstance().getRecord(payeeID);
	}

	@Override
	public List<Payee> getActiveRecords(User user, boolean isActive) {
		
		return DaoFactory.getPayeeInstance().getActiveRecords(user, isActive);
	}

	@Override
	public void updateRecord(Payee payee) {
		DaoFactory.getPayeeInstance().updateRecord(payee);
		
	}
}
