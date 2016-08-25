package com.hibernate.service;

import java.util.List;

import com.hibernate.resource.Budget;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;

public interface IncomeServiceIF 
{
	public void addIncome(Income income);
	
	public void delete(Income income);
	
	public List<Income> getRecords(User user);
	
	public List<Income> getRecords(User user,Budget budget);
	
	public Income getRecord(int incomeID);
	
	public List<Income> getActiveRecords(User user,boolean isActive);
	
	public void updateRecord(Income income);
}
