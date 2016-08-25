package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.Book;

public interface BookDaoIF 
{
	public void addBook(Book book);
	
	public void delete(Book book);
	
	public List<Book> getRecords();
	
	public Book getRecord(int bookID);
	
	public List<Book> getActiveRecords(boolean isActive);
	
	public void updateRecord(Book book);

}
