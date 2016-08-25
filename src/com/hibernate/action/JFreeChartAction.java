package com.hibernate.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.hibernate.resource.Budget;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class JFreeChartAction extends ActionSupport implements SessionAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3153253527075259464L;
	private SessionMap<String, Object> sessionMap;
	
	private User user;
	
	private JFreeChart chart;
	private JFreeChart expenseCategoryChart;
	private JFreeChart monthWiseBarChart;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
		
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	public JFreeChart getExpenseCategoryChart() {
		return expenseCategoryChart;
	}

	public void setExpenseCategoryChart(JFreeChart expenseCategoryChart) {
		this.expenseCategoryChart = expenseCategoryChart;
	}
	public JFreeChart getMonthWiseBarChart() {
		return monthWiseBarChart;
	}
	public void setMonthWiseBarChart(JFreeChart monthWiseBarChart) {
		this.monthWiseBarChart = monthWiseBarChart;
	}
	
	public Budget getCurrentBudget()
	{
		if(sessionMap==null)
		{
			sessionMap=fetchSessionMap();
		}
		User user=(User)sessionMap.get("userName");
		String transactionDate="";
		transactionDate=MyUtils.convertDateToString(new Date(), "dd-MM-yyyy");
		Budget budget=ServiceFactory.getBudgetService().getBudget(user, transactionDate);
		
		return budget;
	}
	public String monthWiseBarChart()
	{
		Budget budget=getCurrentBudget();
		monthWiseBarChart=ServiceFactory.getJFreeChartService().getMonthWiseBarChart(budget);
		return SUCCESS;
	}
	public String execute() throws Exception
	{
		Budget budget=getCurrentBudget();
		
		chart=ServiceFactory.getJFreeChartService().getCategoryPieChart(budget);
		
		return SUCCESS;
	}
	public String expenseCategoryChart()
	{
		Budget budget=getCurrentBudget();
		expenseCategoryChart=ServiceFactory.getJFreeChartService().getExpenseCategoryPieChart(budget);
		return SUCCESS;
	}
	public SessionMap<String, Object> fetchSessionMap()
	{
		if(sessionMap==null)
		{
			sessionMap=(SessionMap<String,Object>)ActionContext.getContext().getSession();
		}
		return sessionMap;
	}
}
