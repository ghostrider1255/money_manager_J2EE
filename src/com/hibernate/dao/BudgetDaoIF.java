package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.Budget;
import com.hibernate.resource.Expense;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;

public interface BudgetDaoIF 
{
	public void addBudget(Budget budget);
	
	public void delete(Budget budget);
	
	public List<Budget> getRecords(User user);
	
	public List<Budget> getDataTablerecords(List<Budget> budgetsList);
	
	public Budget getRecord(int budgetID);
	
	public Budget getBudget(User user,String transactionDate);
	
	public List<Budget> getActiveRecords(User user,boolean isActive);
	
	public void updateRecord(Budget budget);
	
	public void updateBudget(User user,Income income);
	
	public void updateBudget(User user,Expense expense);
	
	public int getBudgetPercentage(Budget budget);
	
	public void updateBudget(User user,Budget budget);
}
