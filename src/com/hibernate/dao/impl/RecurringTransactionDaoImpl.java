package com.hibernate.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.hibernate.dao.RecurringTransactionDaoIF;
import com.hibernate.resource.Expense;
import com.hibernate.resource.Income;
import com.hibernate.resource.RecurringTransaction;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.dbutil.HibernateUtil;

public class RecurringTransactionDaoImpl implements RecurringTransactionDaoIF{

	public RecurringTransaction validateAndAddRecord(RecurringTransaction recurringTransaction)
	{
		try
		{
			if(recurringTransaction!=null)
			{
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
				String transactionFrom=dateFormat.format(recurringTransaction.getTransactionDate());
				
				String transactionThru=dateFormat.format(recurringTransaction.getTranssactionEndDate());
				Date todaysDate=new Date();
				String todaysDateString=dateFormat.format(todaysDate);
				
				if(transactionFrom.compareTo(transactionThru)<0)
				{
					if(transactionFrom.compareTo(todaysDateString)==0)
					{
						if(recurringTransaction.isIncome())
						{
							Income income=new Income();
							
							income.setAmount(recurringTransaction.getAmount());
							income.setCatergory(recurringTransaction.getCatergory());
							income.setDescription(recurringTransaction.getDescription());
							income.setPayee(recurringTransaction.getPayee());
							income.setTransactionDate(recurringTransaction.getTransactionDate());
							income.setUser(recurringTransaction.getUser());
							
							ServiceFactory.getIncomeService().addIncome(income);
						}
						else if(recurringTransaction.isExpense())
						{
							Expense expense=new Expense();
							
							expense.setAmount(recurringTransaction.getAmount());
							expense.setCatergory(recurringTransaction.getCatergory());
							expense.setDescription(recurringTransaction.getDescription());
							expense.setPayee(recurringTransaction.getPayee());
							expense.setTransactionDate(recurringTransaction.getTransactionDate());
							expense.setUser(recurringTransaction.getUser());
							
							ServiceFactory.getExpenseService().addExpense(expense);
						}
						
						recurringTransaction.setNoOfoccurenses(recurringTransaction.getNoOfoccurenses()-1);
						
						//code to add one record into income/expense table
						// also decrease the number of recurrence by one after adding record to income/expense
						if(recurringTransaction.getRecurringTransactionType().getCodeName().toString().compareTo("DAILY")==0)
						{
							Calendar calObj=Calendar.getInstance();
							calObj.setTime(MyUtils.convertToDate(recurringTransaction.getTransactionDate(), "dd-MM-yyyy"));
							calObj.add(Calendar.DATE, 1);
							recurringTransaction.setTransactionDate(MyUtils.convertDateToString(calObj.getTime(), "dd-MM-yyyy"));
							//String updatedDate=new SimpleDateFormat().format();
							
						}
						else if(recurringTransaction.getRecurringTransactionType().getCodeName().toString().compareTo("WEEKLY")==0)
						{
							Calendar calObj=Calendar.getInstance();
							calObj.setTime(MyUtils.convertToDate(recurringTransaction.getTransactionDate(), "dd-MM-yyyy"));
							calObj.add(Calendar.DATE, 7);
							recurringTransaction.setTransactionDate(MyUtils.convertDateToString(calObj.getTime(), "dd-MM-yyyy"));
						}
						else if(recurringTransaction.getRecurringTransactionType().getCodeName().toString().compareTo("MONTHLY")==0)
						{
							Calendar calObj=Calendar.getInstance();
							calObj.setTime(MyUtils.convertToDate(recurringTransaction.getTransactionDate(), "dd-MM-yyyy"));
							calObj.add(Calendar.MONTH, 1);
							recurringTransaction.setTransactionDate(MyUtils.convertDateToString(calObj.getTime(), "dd-MM-yyyy"));
						}
						else if(recurringTransaction.getRecurringTransactionType().getCodeName().toString().compareTo("YEARLY")==0)
						{
							Calendar calObj=Calendar.getInstance();
							calObj.setTime(MyUtils.convertToDate(recurringTransaction.getTransactionDate(), "dd-MM-yyyy"));
							calObj.add(Calendar.YEAR, 1);
							recurringTransaction.setTransactionDate(MyUtils.convertDateToString(calObj.getTime(), "dd-MM-yyyy"));
						}
					}
				}
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return recurringTransaction;
	}
	@Override
	public void addRecurringTransaction(
			RecurringTransaction recurringTransaction) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			if(recurringTransaction!=null && recurringTransaction.isIncome())
			{
				recurringTransaction.setExpense(false);
				recurringTransaction=validateAndAddRecord(recurringTransaction);
				session.save(recurringTransaction);
				session.getTransaction().commit();
			}
			else if(recurringTransaction !=null && recurringTransaction.isExpense())
			{
				recurringTransaction.setIncome(false);
				recurringTransaction=validateAndAddRecord(recurringTransaction);
				session.save(recurringTransaction);
				session.getTransaction().commit();
			}
			
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
	public void delete(RecurringTransaction recurringTransaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RecurringTransaction> getRecords(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecurringTransaction getRecord(int incomeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecurringTransaction> getActiveRecords(User user,
			boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(RecurringTransaction recurringTransaction) {
		// TODO Auto-generated method stub
		
	}

}
