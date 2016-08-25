package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.BudgetDaoIF;
import com.hibernate.resource.Budget;
import com.hibernate.resource.CodeValues;
import com.hibernate.resource.Expense;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.constants.MyConstants;
import com.hibernate.utils.dbutil.HibernateUtil;
import com.hibernate.utils.tablescolumns.BudgetColumn;
import com.hibernate.utils.tablescolumns.CodeValueColumn;

public class BudgetDaoImpl implements BudgetDaoIF
{
	@Override
	public void addBudget(Budget budget) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(budget);
			session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
		}
	}

	@Override
	public void delete(Budget budget) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(budget);
			session.beginTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}		
	}

	@Override
	public List<Budget> getRecords(User user) {
		List<Budget> budgetlist=new ArrayList<Budget>();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			//session.beginTransaction();
			Query query=session.createQuery("from Budget where userID=:userID");
			budgetlist=(List<Budget>)query.setParameter("userID", user.getPersonID()).list();
			//HibernateUtil.commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return budgetlist;
	}
	@Override
	public Budget getBudget(User user,String transactionDate)
	{
		Budget budget=new Budget();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			//HibernateUtil.beginTransaction();
			
			Criteria criteria=session.createCriteria(Budget.class)
					.createAlias("user", "u")
					.add(Restrictions.eq("u.personID",user.getPersonID()))
					.add(Restrictions.sqlRestriction("STR_TO_DATE("+BudgetColumn.BUDGET_START_DATE.toString()+",'%d-%c-%Y')<=STR_TO_DATE('"+transactionDate+"','%d-%c-%Y')"))
					//.add(Restrictions.le(BudgetColumn.BUDGET_START_DATE.toString(), transactionDate))
					//.add(Restrictions.ge(BudgetColumn.BUDGET_END_DATE.toString(), transactionDate));
					.add(Restrictions.sqlRestriction("STR_TO_DATE("+BudgetColumn.BUDGET_END_DATE.toString()+",'%d-%c-%Y')>=STR_TO_DATE('"+transactionDate+"','%d-%c-%Y')"));
			budget=(Budget)criteria.uniqueResult();
			if(budget==null)
			{
				createBudget(user);
				budget=getBudget(user, transactionDate);
			}
			//HibernateUtil.commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return budget;
	}
	@Override
	public Budget getRecord(int budgetID) {
		Budget budget = new Budget();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			HibernateUtil.beginTransaction();
			budget=(Budget)session.get(Budget.class, budgetID);
			HibernateUtil.commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			HibernateUtil.close();
		}
		return budget;
	}
	
	@Override
	public List<Budget> getActiveRecords(User user, boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}
	public void createBudget(User user)
	{
		try
		{
			Budget budget=new Budget();
			budget.setBudgetAmount(0);
			budget.setUser(user);
			String todayDate=MyUtils.computeMonthStartDate(MyUtils.convertDateFormat(MyUtils.convertDateToString(new Date(), "dd-MM-yyyy"), "dd-MM-yyyy", "yyyyMMdd"));
			todayDate=MyUtils.convertDateFormat(todayDate, "yyyyMMdd", "dd-MM-yyyy");
			budget.setBudgetStartDate(todayDate);
			budget.setDefaultStartDate(todayDate);
			todayDate=MyUtils.convertDateFormat(todayDate, "dd-MM-yyyy", "yyyyMMdd");
			todayDate=MyUtils.computeEndDate(todayDate);
			todayDate=MyUtils.convertDateFormat(todayDate, "yyyyMMdd", "dd-MM-yyyy");
			budget.setBudgetEndDate(todayDate);
			budget.setDefaultEndDate(todayDate);
			
			CodeValues budgetStatus=(CodeValues)ServiceFactory.getCodeValuesService().getRecord(CodeValueColumn.CODE_NAME.toString(), MyConstants.BUDGET_STATUS_ACTIVE);
			budget.setBudgetStatus(budgetStatus);
			addBudget(budget);
		}
		catch(Exception exception)
		{
			
		}
	}
	@Override
	public void updateBudget(User user,Income income)
	{
		Budget budget = new Budget();
		Session session=null;
		try
		{
			//session=HibernateUtil.getSession();
			//session.beginTransaction();			
			budget=getBudget(user, income.getTransactionDate());
			updateBudget(user, budget);
			//session.getTransaction().commit();
			
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			//session.close();
		}
	}
	@Override
	public void updateBudget(User user,Expense expense)
	{
		Budget budget = new Budget();
		Session session=null;
		try
		{
			//session=HibernateUtil.getSession();
			//session.beginTransaction();
			budget=getBudget(user, expense.getTransactionDate());
			updateBudget(user, budget);
			//session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			//session.close();
		}
	}
	@Override
	public void updateRecord(Budget budget) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Budget tempBudget=(Budget)session.get(Budget.class, budget.getBudgetID());
			
			tempBudget.setBudgetAmount(budget.getBudgetAmount());
			tempBudget.setIncomeAmount(budget.getIncomeAmount());
			tempBudget.setExpenseAmount(budget.getExpenseAmount());
			tempBudget.setBudgetEndDate(budget.getBudgetEndDate());
			tempBudget.setBudgetStartDate(budget.getBudgetStartDate());
			tempBudget.setDefaultEndDate(budget.getDefaultEndDate());
			tempBudget.setDefaultStartDate(budget.getDefaultStartDate());
			tempBudget.setBudgetStatus(budget.getBudgetStatus());
			tempBudget.setUser(budget.getUser());
			
			session.update(tempBudget);
			session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
		}
	}
	@Override
	public void updateBudget(User user,Budget budget)
	{
		if(budget!=null)
		{
			List<Income> listIncomes=null;
			List<Expense> listExpenses=null;
			
			listIncomes=ServiceFactory.getIncomeService().getRecords(user, budget);
			double totalIncome=0;
			for(Income incomeObj:listIncomes)
			{
				totalIncome+=incomeObj.getAmount();
			}
			
			listExpenses=ServiceFactory.getExpenseService().getRecords(user, budget);
			double totalExpense=0;
			for(Expense expenseObj:listExpenses)
			{
				totalExpense+=expenseObj.getAmount();
			}
			
			budget.setBudgetAmount(totalIncome);
			budget.setIncomeAmount(totalIncome);
			budget.setExpenseAmount(totalExpense);
			updateRecord(budget);
		}
	}
	@Override
	public int getBudgetPercentage(Budget budget)
	{
		int budgetPercentage=0;
		if(budget!=null)
		{
			if(budget.getIncomeAmount()!=0.0)
			{
				budgetPercentage=(int)((budget.getExpenseAmount()/budget.getIncomeAmount())*100);
			}
			else
			{
				budgetPercentage=(int)((budget.getExpenseAmount()/1)*100);
			}
			
		}
		return budgetPercentage;
	}
	@Override
	public List<Budget> getDataTablerecords(List<Budget> budgetsList)
	{
		List<Budget> finalBudgetsList=new ArrayList<Budget>();
		for(Budget budget:budgetsList)
		{
			budget.setBudgetStartDate(MyUtils.convertDateFormat(budget.getBudgetStartDate(), "dd-MM-yyyy", "dd-MMMMM-yyyy"));
			budget.setBudgetEndDate(MyUtils.convertDateFormat(budget.getBudgetEndDate(), "dd-MM-yyyy", "dd-MMMMM-yyyy"));
			finalBudgetsList.add(budget);
		}
		
		return finalBudgetsList;
	}
}
