package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.Book;
import com.hibernate.resource.Chapter;
import com.hibernate.service.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ChapterAction extends ActionSupport implements SessionAware
{
	private int selectedBookID=0;
	private Book book;
	private Chapter chapter;
	
	private List<Chapter> chaptersList=new ArrayList<Chapter>();
	private List<Book> booksList=new ArrayList<Book>();
	private SessionMap<String,Object> sessionMap;
	
	
	public ChapterAction()
	{
		
	}
	public SessionMap<String,Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String,Object> sessionMap) {
		this.sessionMap =  sessionMap;
	}
	public int getSelectedBookID() {
		return selectedBookID;
	}
	public void setSelectedBookID(int selectedBookID) {
		this.selectedBookID = selectedBookID;
	}
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	public List<Chapter> getChaptersList() {
		return chaptersList;
	}
	public void setChaptersList(List<Chapter> chaptersList) {
		this.chaptersList = chaptersList;
	}
	public List<Book> getBooksList() {
		return booksList;
	}
	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	public String initialize()
	{
		if(checkUserLogin())
		{
			book=(Book)ServiceFactory.getBookService().getRecord(selectedBookID);
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
		
	}
	public String execute()
	{
		if(checkUserLogin())
		{
			
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap=sessionMap;
		
	}
	public boolean checkUserLogin()
	{
		 sessionMap=(SessionMap<String,Object>)ActionContext.getContext().getSession();
		boolean isUserLoggedIn=false;
		if(sessionMap!=null && sessionMap.containsKey("userName"))
		{
			isUserLoggedIn=true;
		}
		return isUserLoggedIn;
	}
}
