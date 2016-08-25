package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.Author;
import com.hibernate.service.AuthorServiceIF;

public class AuthorServiceImpl implements AuthorServiceIF
{

	@Override
	public void addAuthor(Author author) {
		DaoFactory.getAuthorInstance().addAuthor(author);
		
	}

	@Override
	public List<Author> getRecords() {
		return DaoFactory.getAuthorInstance().getRecords();
	}

	@Override
	public List<Author> getRecords(String value) {
		return DaoFactory.getAuthorInstance().getRecords(value);
	}

	@Override
	public void delete(Author author) {
		DaoFactory.getAuthorInstance().delete(author);
		
	}

}
