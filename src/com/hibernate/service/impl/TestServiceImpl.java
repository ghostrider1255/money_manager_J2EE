package com.hibernate.service.impl;

import java.util.List;

import com.hibernate.dao.DaoFactory;
import com.hibernate.resource.TestingBean;
import com.hibernate.service.TestServiceIF;

public class TestServiceImpl implements TestServiceIF{

	@Override
	public void addRecord(TestingBean bean) {
		DaoFactory.getTestInstance().addRecord(bean);
	}
	
	@Override
	public List<TestingBean> getRecords()
	{
		return DaoFactory.getTestInstance().getRecords();
	}
}
