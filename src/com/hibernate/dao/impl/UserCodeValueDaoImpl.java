package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.dao.UserCodeValueDaoIF;
import com.hibernate.resource.CodeValues;
import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.dbutil.HibernateUtil;
import com.hibernate.utils.tablescolumns.CodeValueColumn;
import com.hibernate.utils.tablescolumns.UserCodeValueColumn;

public class UserCodeValueDaoImpl implements UserCodeValueDaoIF
{

	@Override
	public void addUserCodeValue(UserCodeValue userCodeValue) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(userCodeValue);
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
	public void delete(UserCodeValue userCodeValue) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(userCodeValue);
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
	public List<UserCodeValue> getRecords(User user) {
		Session session=null;
		List<UserCodeValue> listUserCodeValueRecords=new ArrayList<UserCodeValue>();
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from UserCodeValue where userID=:userID");
			query.setInteger("userID",user.getPersonID());
			listUserCodeValueRecords=query.list();
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
		return listUserCodeValueRecords;
	}
	@Override
	public List<UserCodeValue> getRecords(User user,String columnName,String value) {
		Session session=null;
		List<UserCodeValue> listUserCodeValueRecords=new ArrayList<UserCodeValue>();
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=null;
			if(UserCodeValueColumn.USER_CODE_NAME.compareTo(columnName)==0)
			{
				query=session.createQuery("from UserCodeValue where userCodeName=:userCodeName and userID=:userID");
				query.setString("userCodeName", value);
				query.setInteger("userID", user.getPersonID());
			}
			if(UserCodeValueColumn.USER_CATEGORY.toString().compareTo(columnName)==0)
			{
				CodeValues tempCodeValue=(CodeValues)ServiceFactory.getCodeValuesService().
						getRecord(CodeValueColumn.CODE_NAME.toString(), value);
				query=session.createQuery("from UserCodeValue where userCodeValueID=:userCodeValueID and userID=:userID");
				
				query.setInteger("userCodeValueID", tempCodeValue.getCodeID());
				query.setInteger("userID", user.getPersonID());
			}
			
			listUserCodeValueRecords=(List<UserCodeValue>)query.list();
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
		return listUserCodeValueRecords;
	}
	@Override
	public UserCodeValue getRecord(int userCodeID) {
		UserCodeValue userCodeValue=null;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			userCodeValue=(UserCodeValue)session.get(UserCodeValue.class, userCodeID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return userCodeValue;
	}

	@Override
	public List<UserCodeValue> getActiveRecords(User user,boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(UserCodeValue userCodeValue) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			UserCodeValue tempUserCodeValue=(UserCodeValue)session.get(UserCodeValue.class, userCodeValue.getUserCodeID());
			
			tempUserCodeValue.setUser(userCodeValue.getUser());
			tempUserCodeValue.setUserCodeDesc(userCodeValue.getUserCodeDesc());
			tempUserCodeValue.setCodeValues(userCodeValue.getCodeValues());
			tempUserCodeValue.setUserCodeName(userCodeValue.getUserCodeName());
			
			session.update(tempUserCodeValue);
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
