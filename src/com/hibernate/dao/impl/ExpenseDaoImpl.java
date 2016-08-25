package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.ExpenseDaoIF;
import com.hibernate.resource.Budget;
import com.hibernate.resource.Expense;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.dbutil.HibernateUtil;

public class ExpenseDaoImpl implements ExpenseDaoIF 
{

	@Override
	public void addExpense(Expense expense) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(expense);
			session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			session.getTransaction().rollback();
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public void delete(Expense expense) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(expense);
			session.getTransaction().commit();
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
	public List<Expense> getRecords(User user) {
		List<Expense> expenselist=new ArrayList<Expense>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			Query query=session.createQuery("from Expense where personID=:personID");
			query.setParameter("personID", user.getPersonID());
			expenselist=(List<Expense>)query.list();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return expenselist;
	}
	@Override
	public List<Expense> getRecords(User user,Budget budget)
	{
		List<Expense> listExpense=new ArrayList<Expense>();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			
			Criteria criteria=session.createCriteria(Expense.class)
					.createAlias("user", "u")
					.add(Restrictions.eq("u.personID",user.getPersonID()))
					.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')<=STR_TO_DATE('"+budget.getBudgetEndDate()+"','%d-%c-%Y')"))
					//.add(Restrictions.le(BudgetColumn.BUDGET_START_DATE.toString(), transactionDate))
					//.add(Restrictions.ge(BudgetColumn.BUDGET_END_DATE.toString(), transactionDate));
					.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')>=STR_TO_DATE('"+budget.getBudgetStartDate()+"','%d-%c-%Y')"));
			/*Query query=session.createQuery("from Expense where personID=:personID and transactionDate>=:budgetStartDate and " +
					"transactionDate<=:budgetEndDate order by transactionDate DESC");
			query.setParameter("personID", user.getPersonID());
			query.setParameter("budgetStartDate", budget.getBudgetStartDate());
			query.setParameter("budgetEndDate", budget.getBudgetEndDate());*/
			listExpense=(List<Expense>)criteria.list();
			
		}
		catch(HibernateException exception)
		{
			HibernateUtil.rollback();
			exception.printStackTrace();
		}
		finally
		{
			
		}
		return listExpense;
	}
	@Override
	public Expense getRecord(int expenseID) {
		Expense expense = new Expense();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			expense=(Expense)session.get(Expense.class, expenseID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return expense;
	}

	@Override
	public List<Expense> getActiveRecords(boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(Expense expense) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Expense tempExpense=(Expense)session.get(Expense.class, expense.getExpenseID());
			
			tempExpense.setAmount(expense.getAmount());
			tempExpense.setCatergory(expense.getCatergory());
			tempExpense.setDescription(expense.getDescription());
			tempExpense.setTransactionDate(expense.getTransactionDate());
			tempExpense.setUser(expense.getUser());
			tempExpense.setPayee(expense.getPayee());
			session.update(tempExpense);
			session.getTransaction().commit();
			
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
}
