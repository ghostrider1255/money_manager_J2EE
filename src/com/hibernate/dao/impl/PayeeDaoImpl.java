package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.dao.PayeeDaoIF;
import com.hibernate.resource.CodeValues;
import com.hibernate.resource.Income;
import com.hibernate.resource.Payee;
import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.dbutil.HibernateUtil;
import com.hibernate.utils.tablescolumns.CodeValueColumn;
import com.hibernate.utils.tablescolumns.PayeeColumn;
import com.hibernate.utils.tablescolumns.UserCodeValueColumn;

public class PayeeDaoImpl implements PayeeDaoIF
{

	@Override
	public void addPayee(Payee payee) {

		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(payee);
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
	public void delete(Payee payee) {

		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(payee);
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
	public List<Payee> getRecords(User user) {

		List<Payee> incomeslist=new ArrayList<Payee>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from Payee where userID=:userID");
			incomeslist=(List<Payee>)query.setParameter("userID", user.getPersonID()).list();
			
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
		
		return incomeslist;
	
	}

	@Override
	public List<Payee> getRecords(User user, String columnName, String value) {

		Session session=null;
		List<Payee> userPayeeList=new ArrayList<Payee>();
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=null;
			if(PayeeColumn.PAYEE_NAME.compareTo(columnName)==0)
			{
				query=session.createQuery("from Payee where payeeName=:payeeName and userID=:userID");
				query.setString("payeeName", value);
				query.setInteger("userID", user.getPersonID());
			}
			if(PayeeColumn.PAYEE_CATEGORY.toString().compareTo(columnName)==0)
			{
				CodeValues tempCodeValue=(CodeValues)ServiceFactory.getCodeValuesService().
						getRecord(CodeValueColumn.CODE_NAME.toString(), value);
				query=session.createQuery("from Payee where payeeCateogryID=:payeeCateogryID and userID=:userID");
				
				query.setInteger("payeeCateogryID", tempCodeValue.getCodeID());
				query.setInteger("userID", user.getPersonID());
			}
			
			userPayeeList=(List<Payee>)query.list();
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
		return userPayeeList;
	
	}

	@Override
	public Payee getRecord(int payeeID) {
		Payee payee = new Payee();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			payee=(Payee)session.get(Payee.class, payeeID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return payee;
	
	}

	@Override
	public List<Payee> getActiveRecords(User user, boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(Payee payee) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Payee tempPayee=(Payee)session.get(Payee.class, payee.getPayeeID());
			
			tempPayee.setPayeeName(payee.getPayeeName());
			tempPayee.setPayeeDesc(payee.getPayeeDesc());
			tempPayee.setPayeeStatus(payee.isPayeeStatus());
			tempPayee.setUser(payee.getUser());
			tempPayee.setPayeeCateogry(payee.getPayeeCateogry());
			session.update(tempPayee);
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
