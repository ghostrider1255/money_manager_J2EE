package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.Book;
import com.hibernate.resource.CodeGroup;
import com.hibernate.resource.CodeValues;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.constants.ConfigProperties;
import com.hibernate.utils.constants.MyConstants;
import com.hibernate.utils.tablescolumns.CodeValueColumn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BookAction extends ActionSupport implements SessionAware
{
	private SessionMap<String,Object> sessionMap;
	
	private Book book = new Book();
	private int selectedBookID=0;
	private int selectedCategoryID=0;
	private List<CodeValues> categoryList=new ArrayList<CodeValues>();
	private List<Book> booksList=new ArrayList<Book>();
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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

	public List<CodeValues> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CodeValues> categoryList) {
		this.categoryList = categoryList;
	}
	
	public List<Book> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Book> booksList) {
		this.booksList = booksList;
	}
	
	public int getSelectedCategoryID() {
		return selectedCategoryID;
	}

	public void setSelectedCategoryID(int selectedCategoryID) {
		this.selectedCategoryID = selectedCategoryID;
	}
	
	public void refresh()
	{
		book=(Book)ServiceFactory.getBookService().getRecord(selectedBookID);
		booksList=(List<Book>)ServiceFactory.getBookService().getRecords();
		categoryList=(List<CodeValues>)ServiceFactory.getCodeValuesService().
				getRecords(CodeValueColumn.CODE_GROUP.toString(),MyConstants.BOOK_CATEGORY.toString());
	}
	public String initialize()
	{
		if(checkUserLogin())
		{
			categoryList=(List<CodeValues>)ServiceFactory.getCodeValuesService().
					getRecords(CodeValueColumn.CODE_GROUP.toString(),MyConstants.BOOK_CATEGORY.toString());
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
			if(book == null)
			{
				book = new Book();
			}
			CodeValues tempCategory=(CodeValues)ServiceFactory.getCodeValuesService().getRecord(selectedCategoryID);
			book.setCategoryCodeValue(tempCategory);
			ServiceFactory.getBookService().addBook(book);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
		
	}
	public String viewBooks()
	{
		if(checkUserLogin())
		{
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
		
	}
	public String deleteBook()
	{
		if(checkUserLogin())
		{
			Book tempbook=(Book)ServiceFactory.getBookService().getRecord(selectedBookID);
			ServiceFactory.getBookService().delete(tempbook);
			refresh();
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
