package com.hibernate.service;

import java.util.List;

import com.hibernate.resource.Budget;
import com.hibernate.resource.Expense;
import com.hibernate.resource.User;

public interface ExpenseServiceIF 
{
	public void addExpense(Expense expense);
	
	public void delete(Expense expense);
	
	public List<Expense> getRecords(User user);
	
	public List<Expense> getRecords(User user,Budget budget);
	
	public Expense getRecord(int incomeID);
	
	public List<Expense> getActiveRecords(boolean isActive);
	
	public void updateRecord(Expense expense);

}
