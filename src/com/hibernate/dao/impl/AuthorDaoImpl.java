package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.dao.AuthorDaoIF;
import com.hibernate.resource.Author;
import com.hibernate.utils.dbutil.HibernateUtil;
import com.hibernate.utils.tablescolumns.AuthorColumn;

public class AuthorDaoImpl implements AuthorDaoIF
{

	@Override
	public void addAuthor(Author author) 
	{
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(author);
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
	public List<Author> getRecords() {
		Session session=null;
		List<Author> listRecords=new ArrayList<Author>();
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from Author");
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
	public List<Author> getRecords(String value) {
		Session session=null;
		List<Author> listRecords=new ArrayList<Author>();
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=null;
			if(value.compareTo(AuthorColumn.AUTHOR_ID.toString()) == 0)
			{
				query=session.createQuery("from Author where authorID=:authorID");
				query.setString("authorID", value);
			}
			else if(value.compareTo(AuthorColumn.AUTHOR_NAME.toString()) == 0)
			{
				query=session.createQuery("from Author where authorName=:authorName");
				query.setString("authorName", value);
			}
			else if(value.compareTo(AuthorColumn.AUTHOR_STATUS.toString()) == 0)
			{
				query=session.createQuery("from Author where authorStatus=:authorStatus");
				query.setString("authorStatus", value);
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
	
	@Override
	public void delete(Author author) 
	{
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(author);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
	}

}
