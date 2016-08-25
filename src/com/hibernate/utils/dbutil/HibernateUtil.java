package com.hibernate.utils.dbutil;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class HibernateUtil 
{
	private static SessionFactory sessionFactory;
	
	static
	{
		try
		{
			Configuration configuration=new Configuration();
			configuration.configure("hibernate.cfg.xml");
			ServiceRegistryBuilder serviceRegistryBuilder=new ServiceRegistryBuilder();
			serviceRegistryBuilder.applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry=serviceRegistryBuilder.buildServiceRegistry();
			//new SchemaExport(configuration).create(true,true);
			sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		}
		catch(HibernateException hibernateException)
		{
			hibernateException.printStackTrace();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private static SessionFactory getSessionFactory() throws HibernateException
	{
		return sessionFactory;
	}
	
	public static Session getSession() throws HibernateException
	{
		Session session=null;
		if(sessionFactory==null)
		{
			sessionFactory=getSessionFactory();
		}
		try
		{
			session=sessionFactory.getCurrentSession();
		}
		catch(HibernateException hibException)
		{
			session=sessionFactory.openSession();
		}
		
		return session;
	}
	public static void close()
	{
		//HibernateUtil.getSessionFactory().close();
	}
	public static void rollback()
	{
		HibernateUtil.getSession().getTransaction().rollback();
	}
	public static void commit()
	{
		HibernateUtil.getSession().getTransaction().commit();
	}
	public static void beginTransaction()
	{
		HibernateUtil.getSession().beginTransaction();
	}
}
