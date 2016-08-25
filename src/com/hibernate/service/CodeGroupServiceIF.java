package com.hibernate.service;

import java.util.List;

import com.hibernate.resource.CodeGroup;

public interface CodeGroupServiceIF 
{
	public void addGroup(CodeGroup codeGroup);
	
	public void delete(CodeGroup codeGroup);
	
	public List<CodeGroup> getRecords();
	
	public CodeGroup getRecord(int codeGroupID);
	
	public List<CodeGroup> getActiveRecords(boolean isActive);
	
	public void updateRecord(CodeGroup codeGroup);
	
	public CodeGroup getRecord(String Value,String columnName);

}
