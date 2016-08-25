package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.Budget;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;
import com.hibernate.service.IncomeServiceIF;

public class IncomeServiceImpl implements IncomeServiceIF
{

	@Override
	public void addIncome(Income income) {
		DaoFactory.getIncomeInstance().addIncome(income);
		
	}

	@Override
	public void delete(Income income) {
		DaoFactory.getIncomeInstance().delete(income);
		
	}

	@Override
	public List<Income> getRecords(User user) {
		
		return DaoFactory.getIncomeInstance().getRecords(user);
	}

	@Override
	public Income getRecord(int incomeID) {
		
		return DaoFactory.getIncomeInstance().getRecord(incomeID);
	}

	@Override
	public List<Income> getActiveRecords(User user,boolean isActive) {
		
		return DaoFactory.getIncomeInstance().getActiveRecords(user,isActive);
	}

	@Override
	public void updateRecord(Income income) {
		DaoFactory.getIncomeInstance().updateRecord(income);
		
	}

	@Override
	public List<Income> getRecords(User user, Budget budget) {
		return DaoFactory.getIncomeInstance().getRecords(user, budget);
	}

}
