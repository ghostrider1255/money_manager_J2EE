package com.hibernate.service;

import java.util.List;

import com.hibernate.resource.TestingBean;

public interface TestServiceIF 
{
	public void addRecord(TestingBean bean);
	
	public List<TestingBean> getRecords();

}
