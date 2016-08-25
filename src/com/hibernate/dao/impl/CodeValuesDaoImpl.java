package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.CodeValuesDaoIF;
import com.hibernate.resource.CodeGroup;
import com.hibernate.resource.CodeValues;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.dbutil.HibernateUtil;
import com.hibernate.utils.tablescolumns.CodeGroupColumn;
import com.hibernate.utils.tablescolumns.CodeValueColumn;

public class CodeValuesDaoImpl implements CodeValuesDaoIF{

	@Override
	public void addCodeValue(CodeValues codeValue) {
		
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(codeValue);
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
	public List<CodeValues> getRecords() {
		Session session=null;
		List<CodeValues> listRecords=new ArrayList<CodeValues>();
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from CodeValues");
			listRecords=query.list();
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
		return listRecords;
	}
	
	@Override
	public CodeValues getRecord(int codeID)
	{
		CodeValues codeValue=null;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			codeValue=(CodeValues)session.get(CodeValues.class, codeID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return codeValue;
	}
	@Override
	public CodeValues getRecord(String columnName,String value)
	{
		Session session=null;
		CodeValues codeValue=new CodeValues();
		try
		{
			session=HibernateUtil.getSession();
			//session.beginTransaction();
			Query query=null;
			if(columnName.compareTo(CodeValueColumn.CODE_NAME.toString()) == 0)
			{
				Criteria criteria=session.createCriteria(CodeValues.class).add(Restrictions.eq(columnName,value));
				codeValue=(CodeValues)criteria.uniqueResult();
				
			}
			else if(columnName.compareTo(CodeValueColumn.CODE_ID.toString()) == 0)
			{
				Criteria criteria=session.createCriteria(CodeValues.class).add(Restrictions.eq(columnName,value));
				codeValue=(CodeValues)criteria.uniqueResult();
			}
			
			//session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return codeValue;
	}
	@Override
	public List<CodeValues> getRecords(String columnName,String value) {
		Session session=null;
		List<CodeValues> listRecords=new ArrayList<CodeValues>();
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=null;
			if(columnName.compareTo(CodeValueColumn.CODE_NAME.toString()) == 0)
			{
				query=session.createQuery("from CodeValues where codeName=:codeName");
				query.setString("codeName", value);
			}
			else if(columnName.compareTo(CodeValueColumn.CODE_GROUP.toString()) == 0)
			{
				CodeGroup tempCodeGroup=(CodeGroup)ServiceFactory.getCodeGroupService().getRecord(value, CodeGroupColumn.GROUP_CODE.toString());
				query=session.createQuery("from CodeValues where groupID like :groupID");
				if(tempCodeGroup==null)
				{
					query.setInteger("groupID", 0);
				}
				else
				{
					query.setInteger("groupID", tempCodeGroup.getGroupID());
				}
				
			}
			else if(columnName.compareTo(CodeValueColumn.CODE_ID.toString()) == 0)
			{
				query=session.createQuery("from CodeValues where codeID=:codeID");
				query.setString("codeID", value);
			}
			
			listRecords=query.list();
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
		return listRecords;
	}

	public void delete(CodeValues codeValue) 
	{
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(codeValue);
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
	public void update(CodeValues codeValue) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			CodeValues tempCodeValue=(CodeValues)session.get(CodeValues.class, codeValue.getCodeID());
			tempCodeValue.setCodeName(codeValue.getCodeName());
			tempCodeValue.setCodeDesc(codeValue.getCodeDesc());
			tempCodeValue.setCodeGroup(codeValue.getCodeGroup());
			session.update(tempCodeValue);
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
	public List<CodeValues> getActiveRecords(boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}
}
