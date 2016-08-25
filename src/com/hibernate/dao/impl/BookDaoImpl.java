package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.dao.BookDaoIF;
import com.hibernate.resource.Book;
import com.hibernate.resource.CodeGroup;
import com.hibernate.utils.dbutil.HibernateUtil;

public class BookDaoImpl implements BookDaoIF{

	@Override
	public void addBook(Book book) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(book);
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
	public void delete(Book book) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(book);
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
	public List<Book> getRecords() {
		List<Book> bookslist=new ArrayList<Book>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from Book");
			bookslist=query.list();
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
		
		return bookslist;
	}

	@Override
	public Book getRecord(int bookID) {
		Book book = new Book();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			book=(Book)session.get(Book.class, bookID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return book;
	}

	@Override
	public List<Book> getActiveRecords(boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(Book book) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Book tempBook=(Book)session.get(Book.class, book.getBookID());
			
			tempBook.setBookName(book.getBookName());
			tempBook.setAuthor(book.getAuthor());
			tempBook.setStatus(book.isStatus());
			tempBook.setCategoryCodeValue(book.getCategoryCodeValue());
			tempBook.setChaptersList(book.getChaptersList());
			
			session.getTransaction().commit();
			session.update(tempBook);
			
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
