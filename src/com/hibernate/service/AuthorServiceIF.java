package com.hibernate.service;

import java.util.List;

import com.hibernate.resource.Author;

public interface AuthorServiceIF 
{
	public void addAuthor(Author author);
	
	public List<Author> getRecords();
	
	public List<Author> getRecords(String value);
	
	public void delete(Author author);

}
