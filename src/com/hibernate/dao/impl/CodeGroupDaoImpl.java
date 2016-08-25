package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.CodeGroupDaoIF;
import com.hibernate.resource.CodeGroup;
import com.hibernate.utils.dbutil.HibernateUtil;
import com.hibernate.utils.tablescolumns.CodeGroupColumn;

public class CodeGroupDaoImpl implements CodeGroupDaoIF
{

	@Override
	public void addGroup(CodeGroup codeGroup) 
	{
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(codeGroup);
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
	public void delete(CodeGroup codeGroup) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(codeGroup);
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
	public List<CodeGroup> getRecords() {
		List<CodeGroup> list=new ArrayList<CodeGroup>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from CodeGroup");
			list=query.list();
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
		
		return list;
	}
	public CodeGroup getRecord(int codeGroupID)
	{
		CodeGroup codeGroup = new CodeGroup();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			codeGroup=(CodeGroup)session.get(CodeGroup.class, codeGroupID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return codeGroup;
	}
	@Override
	public CodeGroup getRecord(String Value,String columnName)
	{
		CodeGroup codeGroup= new CodeGroup();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			if(columnName.compareTo(CodeGroupColumn.GROUP_CODE.toString())==0)
			{
				Criteria criteria=session.createCriteria(CodeGroup.class).add(Restrictions.eq(CodeGroupColumn.GROUP_CODE.toString(),Value));
				codeGroup=(CodeGroup)criteria.uniqueResult();
				/*Query query=session.createQuery("from CodeGroup where groupCode=:groupCode");
				CodeGroup=(CodeGroup)query.setParameter("groupCode",Value).get;*/
			}
			else if(columnName.compareTo(CodeGroupColumn.GROUP_DESC.toString())==0)
			{
				Criteria criteria=session.createCriteria(CodeGroup.class).add(Restrictions.eq(CodeGroupColumn.GROUP_DESC.toString(),Value));
				codeGroup=(CodeGroup)criteria.uniqueResult();
				/*Query query=session.createQuery("from CodeGroup where groupDesc=:groupDesc");
				codeGroupList=(List<CodeGroup>)query.setParameter("groupDesc",Value).list();*/
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
		return codeGroup;
	}
	public List<CodeGroup> getRecords(String Value,String columnName)
	{
		List<CodeGroup> codeGroupList = new ArrayList<CodeGroup>();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			if(columnName.compareTo(CodeGroupColumn.GROUP_CODE.toString())==0)
			{
				Query query=session.createQuery("from CodeGroup where groupCode like :groupCode");
				codeGroupList=(List<CodeGroup>)query.setParameter("groupCode",Value+"%").list();
			}
			else if(columnName.compareTo(CodeGroupColumn.GROUP_DESC.toString())==0)
			{
				Query query=session.createQuery("from CodeGroup where groupDesc like :groupDesc");
				codeGroupList=(List<CodeGroup>)query.setParameter("groupDesc",Value+"%").list();
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
		return codeGroupList;
	}

	@Override
	public void updateRecord(CodeGroup codeGroup) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			CodeGroup tempcodeGroup=(CodeGroup)session.get(CodeGroup.class, codeGroup.getGroupID());
			
			tempcodeGroup.setGroupCode(codeGroup.getGroupCode());
			tempcodeGroup.setGroupDesc(codeGroup.getGroupDesc());
			tempcodeGroup.setGroupStatus(codeGroup.isGroupStatus());
			
			session.getTransaction().commit();
			session.update(tempcodeGroup);
			
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
	public List<CodeGroup> getActiveRecords(boolean isActive) {
		List<CodeGroup> list=new ArrayList<CodeGroup>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from CodeGroup where groupStatus like :groupStatus");
			list=(List<CodeGroup>)query.setParameter("groupStatus",isActive).list();
			session.getTransaction().commit();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return list;
	}

}
