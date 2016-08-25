package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.CodeGroup;
import com.hibernate.service.CodeGroupServiceIF;

public class CodeGroupServiceImpl implements CodeGroupServiceIF{

	@Override
	public void addGroup(CodeGroup codeGroup) {
		DaoFactory.getCodeGroupInstance().addGroup(codeGroup);
		
	}

	@Override
	public void delete(CodeGroup codeGroup) {
		DaoFactory.getCodeGroupInstance().delete(codeGroup);
		
	}

	@Override
	public List<CodeGroup> getRecords() {
		
		return DaoFactory.getCodeGroupInstance().getRecords();
	}

	@Override
	public CodeGroup getRecord(int codeGroupID) {
		
		return DaoFactory.getCodeGroupInstance().getRecord(codeGroupID);
	}

	@Override
	public void updateRecord(CodeGroup codeGroup) {
		DaoFactory.getCodeGroupInstance().updateRecord(codeGroup);
		
	}

	@Override
	public List<CodeGroup> getActiveRecords(boolean isActive) {
		
		return DaoFactory.getCodeGroupInstance().getActiveRecords(isActive);
	}

	@Override
	public CodeGroup getRecord(String Value, String columnName) {
		
		return DaoFactory.getCodeGroupInstance().getRecord(Value, columnName);
	}

}
