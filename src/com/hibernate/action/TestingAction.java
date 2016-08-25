package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;

import com.hibernate.resource.TestingBean;
import com.hibernate.service.ServiceFactory;

public class TestingAction 
{
	public String name;
	public TestingBean bean;
	public List<TestingBean> testList=null;
	
	public TestingAction()
	{
		
	}
	
	public List<TestingBean> getTestList() 
	{
		testList=ServiceFactory.getTestService().getRecords();
		return testList;
	}
	public void setTestList(List<TestingBean> testList) {
		this.testList = testList;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TestingBean getBean() {
		return bean;
	}
	public void setBean(TestingBean bean) {
		this.bean = bean;
	}
	
	public String getRecords()
	{
		String result="success";
		testList=new ArrayList<TestingBean>();
		
		testList=ServiceFactory.getTestService().getRecords();
		
		return result;
	}
	public String execute()
	{
		bean=new TestingBean();
		bean.setName(name);
		ServiceFactory.getTestService().addRecord(bean);
		return "success";
	}
	
}
