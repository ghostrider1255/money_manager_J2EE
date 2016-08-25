package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.Budget;
import com.hibernate.resource.Expense;
import com.hibernate.resource.User;
import com.hibernate.service.ExpenseServiceIF;

public class ExpenseServiceImpl implements ExpenseServiceIF{

	@Override
	public void addExpense(Expense expense) {
		DaoFactory.getExpenseInstance().addExpense(expense);
		
	}

	@Override
	public void delete(Expense expense) {
		DaoFactory.getExpenseInstance().delete(expense);
		
	}

	@Override
	public List<Expense> getRecords(User user) {
		
		return DaoFactory.getExpenseInstance().getRecords(user);
	}

	@Override
	public Expense getRecord(int incomeID) {
		
		return DaoFactory.getExpenseInstance().getRecord(incomeID);
	}

	@Override
	public List<Expense> getActiveRecords(boolean isActive) {
		
		return DaoFactory.getExpenseInstance().getActiveRecords(isActive);
	}

	@Override
	public void updateRecord(Expense expense) {
		DaoFactory.getExpenseInstance().updateRecord(expense);
		
	}

	@Override
	public List<Expense> getRecords(User user, Budget budget) {
		return DaoFactory.getExpenseInstance().getRecords(user, budget);
	}

}
