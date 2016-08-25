package com.hibernate.dao;

import java.util.List;

import com.hibernate.resource.TestingBean;

public interface TestDaoIF 
{
	public void addRecord(TestingBean bean); 
	
	public List<TestingBean> getRecords();
}
