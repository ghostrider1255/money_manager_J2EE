package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.Book;
import com.hibernate.service.BookServiceIF;

public class BookServiceImpl implements BookServiceIF
{

	@Override
	public void addBook(Book book) {
		DaoFactory.getBookInstance().addBook(book);
		
	}

	@Override
	public void delete(Book book) {
		DaoFactory.getBookInstance().delete(book);
		
	}

	@Override
	public List<Book> getRecords() {
		
		return DaoFactory.getBookInstance().getRecords();
	}

	@Override
	public Book getRecord(int bookID) {
		
		return DaoFactory.getBookInstance().getRecord(bookID);
	}

	@Override
	public List<Book> getActiveRecords(boolean isActive) {
		return DaoFactory.getBookInstance().getActiveRecords(isActive);
	}

	@Override
	public void updateRecord(Book book) {
		DaoFactory.getBookInstance().updateRecord(book);
		
	}

}
