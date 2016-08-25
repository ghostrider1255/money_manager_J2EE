package com.hibernate.service;

import java.util.List;

import com.hibernate.resource.CodeValues;

public interface CodeValuesServiceIF 
{
	public void addCodeValue(CodeValues codeValue);
	
	public List<CodeValues> getRecords();
	
	public CodeValues getRecord(int codeID);
	
	public List<CodeValues> getRecords(String columnName,String value);
	
	public CodeValues getRecord(String columnName,String value);
	
	public void delete(CodeValues codeValue);
	
	public List<CodeValues> getActiveRecords(boolean isActive);
	
	public void update(CodeValues codeValue);
}
