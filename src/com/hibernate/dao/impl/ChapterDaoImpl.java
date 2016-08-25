package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.dao.ChapterDaoIF;
import com.hibernate.resource.Book;
import com.hibernate.resource.Chapter;
import com.hibernate.utils.dbutil.HibernateUtil;

public class ChapterDaoImpl implements ChapterDaoIF{

	@Override
	public void addChapter(Chapter chapter) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(chapter);
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
	public void delete(Chapter chapter) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(chapter);
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
	public List<Chapter> getRecords() {
		List<Chapter> chapterslist=new ArrayList<Chapter>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from Chapter");
			chapterslist=query.list();
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
		
		return chapterslist;
	}

	@Override
	public Chapter getRecord(int chapterID) {
		Chapter chapter = new Chapter();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			chapter=(Chapter)session.get(Chapter.class, chapterID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return chapter;
	}

	@Override
	public List<Chapter> getActiveRecords(boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(Chapter chapter) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Chapter tempChapter=(Chapter)session.get(Chapter.class, chapter.getChapterID());
			
			tempChapter.setChapterName(chapter.getChapterName());
			tempChapter.setChapterStatus(chapter.isChapterStatus());
			
			session.getTransaction().commit();
			session.update(tempChapter);
			
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
