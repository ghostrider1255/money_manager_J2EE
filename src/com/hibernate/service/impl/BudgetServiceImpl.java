package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.Budget;
import com.hibernate.resource.Expense;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;
import com.hibernate.service.BudgetServiceIF;

public class BudgetServiceImpl implements BudgetServiceIF 
{

	@Override
	public void addBudget(Budget budget) {
		DaoFactory.getBudgetInstance().addBudget(budget);
		
	}

	@Override
	public void delete(Budget budget) {
		DaoFactory.getBudgetInstance().delete(budget);
		
	}

	@Override
	public List<Budget> getRecords(User user) {
		
		return DaoFactory.getBudgetInstance().getRecords(user);
	}

	@Override
	public Budget getRecord(int budgetID) {
		return DaoFactory.getBudgetInstance().getRecord(budgetID);
	}

	@Override
	public List<Budget> getActiveRecords(User user, boolean isActive) {
		return DaoFactory.getBudgetInstance().getActiveRecords(user, isActive);
	}

	@Override
	public void updateRecord(Budget budget) {
		DaoFactory.getBudgetInstance().updateRecord(budget);
	}

	@Override
	public void updateBudget(User user, Income income) {
		DaoFactory.getBudgetInstance().updateBudget(user, income);
		
	}

	@Override
	public void updateBudget(User user, Expense expense) {
		DaoFactory.getBudgetInstance().updateBudget(user, expense);
		
	}

	@Override
	public Budget getBudget(User user, String transactionDate) {
		return DaoFactory.getBudgetInstance().getBudget(user, transactionDate);
	}

	@Override
	public int getBudgetPercentage(Budget budget) {
		return DaoFactory.getBudgetInstance().getBudgetPercentage(budget);
	}

	@Override
	public void updateBudget(User user, Budget budget) {
		DaoFactory.getBudgetInstance().updateBudget(user, budget);
	}

	@Override
	public List<Budget> getDataTablerecords(List<Budget> budgetsList) {
		return DaoFactory.getBudgetInstance().getDataTablerecords(budgetsList);
	}

}
