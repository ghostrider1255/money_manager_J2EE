package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.Author;

public interface AuthorDaoIF 
{
	public void addAuthor(Author author);
	
	public List<Author> getRecords();
	
	public List<Author> getRecords(String value);
	
	public void delete(Author author);

}
