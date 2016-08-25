package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.CodeValues;
import com.hibernate.service.CodeValuesServiceIF;

public class CodeValuesServiceImpl implements CodeValuesServiceIF
{

	@Override
	public void addCodeValue(CodeValues codeValue) {
		DaoFactory.getCodeValuesInstance().addCodeValue(codeValue);
		
	}

	@Override
	public List<CodeValues> getRecords() {
		return DaoFactory.getCodeValuesInstance().getRecords();
	}

	@Override
	public List<CodeValues> getRecords(String columnName,String value) {
		
		return DaoFactory.getCodeValuesInstance().getRecords(columnName,value);
	}

	@Override
	public void delete(CodeValues codeValue) {
		DaoFactory.getCodeValuesInstance().delete(codeValue);
		
	}

	@Override
	public CodeValues getRecord(int codeID) {
		return DaoFactory.getCodeValuesInstance().getRecord(codeID);
	}

	@Override
	public void update(CodeValues codeValue) {
		DaoFactory.getCodeValuesInstance().update(codeValue);
		
	}

	@Override
	public List<CodeValues> getActiveRecords(boolean isActive) {
		return DaoFactory.getCodeValuesInstance().getActiveRecords(isActive);
	}

	@Override
	public CodeValues getRecord(String columnName, String value) {
		return DaoFactory.getCodeValuesInstance().getRecord(columnName, value);
	}

}
