package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.dao.TestDaoIF;
import com.hibernate.resource.TestingBean;
import com.hibernate.utils.dbutil.HibernateUtil;

public class TestDaoImpl implements TestDaoIF{

	@Override
	public void addRecord(TestingBean bean) {
		
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(bean);
			session.getTransaction().commit();
		}
		catch(HibernateException he)
		{
		}
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TestingBean> getRecords()
	{
		List<TestingBean> list=new ArrayList<TestingBean>();
		//SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from TestingBean");
			/*query=query.setFirstResult(pageSize*(pageNumber-1));
			query.setMaxResults(pageSize);*/
			list=query.list();
			System.out.println(list.size());
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
