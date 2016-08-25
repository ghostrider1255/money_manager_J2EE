package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.IncomeDaoIF;
import com.hibernate.resource.Budget;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;
import com.hibernate.utils.dbutil.HibernateUtil;

public class IncomeDaoImpl implements IncomeDaoIF
{

	@Override
	public void addIncome(Income income) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			//HibernateUtil.beginTransaction();
			session.beginTransaction();
			session.save(income);
			session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			session.getTransaction().rollback();
			hiberException.printStackTrace();
		}
		finally
		{
			//HibernateUtil.close();
			session.close();
		}
	}

	@Override
	public void delete(Income income) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(income);
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
	public List<Income> getRecords(User user,Budget budget)
	{
		List<Income> listIncomes=new ArrayList<Income>();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			
			Criteria criteria=session.createCriteria(Income.class)
					.createAlias("user", "u")
					.add(Restrictions.eq("u.personID",user.getPersonID()))
					.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')<=STR_TO_DATE('"+budget.getBudgetEndDate()+"','%d-%c-%Y')"))
					//.add(Restrictions.le(BudgetColumn.BUDGET_START_DATE.toString(), transactionDate))
					//.add(Restrictions.ge(BudgetColumn.BUDGET_END_DATE.toString(), transactionDate));
					.add(Restrictions.sqlRestriction("STR_TO_DATE(transactionDate,'%d-%c-%Y')>=STR_TO_DATE('"+budget.getBudgetStartDate()+"','%d-%c-%Y')"))
					.addOrder(Order.desc("transactionDate"));
			
			//SessionService.beginTransaction(session);
			/*Query query=session.createQuery("from Income where personID=:personID and transactionDate>=:budgetStartDate and " +
					"transactionDate<=:budgetEndDate order by transactionDate DESC");
			query.setParameter("personID", user.getPersonID());
			query.setParameter("budgetStartDate", budget.getBudgetStartDate());
			query.setParameter("budgetEndDate", budget.getBudgetEndDate());*/
			
			
			listIncomes=(List<Income>)criteria.list();
			
			//SessionService.commitTransaction();
		}
		catch(HibernateException exception)
		{
			exception.printStackTrace();
		}
		finally
		{
			
		}
		return listIncomes;
	}
	@Override
	public List<Income> getRecords(User user) {
		List<Income> incomeslist=new ArrayList<Income>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			Query query=session.createQuery("from Income where personID=:personID order by transactionDate DESC");
			incomeslist=(List<Income>)query.setParameter("personID", user.getPersonID()).list();
			
			
			/*Criteria criteria=session.createCriteria(Income.class)
					.createAlias("user", "u")
					.add(Restrictions.eq("u.personID",user.getPersonID()))
					.add(Restrictions.sqlRestriction("order by str_to_date(transactionDate,'%d-%c-%Y') desc"));
			
			incomeslist=(List<Income>)criteria.list();*/
			
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return incomeslist;
	}

	@Override
	public Income getRecord(int incomeID) {
		Income income = new Income();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			income=(Income)session.get(Income.class, incomeID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return income;
	}

	@Override
	public List<Income> getActiveRecords(User user,boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(Income income) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Income tempIncome=(Income)session.get(Income.class, income.getIncomeID());
			
			tempIncome.setAmount(income.getAmount());
			tempIncome.setCatergory(income.getCatergory());
			tempIncome.setDescription(income.getDescription());
			tempIncome.setTransactionDate(income.getTransactionDate());
			tempIncome.setUser(income.getUser());
			tempIncome.setPayee(income.getPayee());
			
			session.update(tempIncome);
			
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
}
