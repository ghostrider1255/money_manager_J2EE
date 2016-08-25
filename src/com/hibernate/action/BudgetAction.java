package com.hibernate.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.Budget;
import com.hibernate.resource.CodeValues;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.constants.MyConstants;
import com.hibernate.utils.tablescolumns.CodeValueColumn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BudgetAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2420480883889552295L;
	private int budgetID;
	private Budget budget;
	private int budgetProgress;// budget completed percentage
	private int userCodeID;
	private User user;
	private List<Budget> budgetList;
	private List<CodeValues> budgetStatus;	
	private SessionMap<String,Object> sessionMap;
	
	public BudgetAction()
	{}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
		
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public SessionMap<String,Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String,Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public int getBudgetProgress() {
		return budgetProgress;
	}

	public void setBudgetProgress(int budgetProgress) {
		this.budgetProgress = budgetProgress;
	}

	public List<Budget> getBudgetList() {
		return budgetList;
	}

	public void setBudgetList(List<Budget> budgetList) {
		this.budgetList = budgetList;
	}

	public List<CodeValues> getBudgetStatus() {
		return budgetStatus;
	}

	public void setBudgetStatus(List<CodeValues> budgetStatus) {
		this.budgetStatus = budgetStatus;
	}

	public int getBudgetID() {
		return budgetID;
	}

	public void setBudgetID(int budgetID) {
		this.budgetID = budgetID;
	}
	
	public int getUserCodeID() {
		return userCodeID;
	}

	public void setUserCodeID(int userCodeID) {
		this.userCodeID = userCodeID;
	}	
	
	public String initialize()
	{
		if(checkUserLogin())
		{
			try
			{
				Date currentDate=new Date();
				
				if(sessionMap==null)
				{
					sessionMap=fetchSessionMap();
				}
				User tempUser=(User)sessionMap.get("userName");
				setUser(tempUser);
				
				budget=ServiceFactory.getBudgetService().getBudget(tempUser, MyUtils.convertDateToString(currentDate, "dd-MM-yyyy"));
				budgetProgress=ServiceFactory.getBudgetService().getBudgetPercentage(budget);
				budgetStatus=(List<CodeValues>)ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP, MyConstants.BUDGET_STATUS);
				
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
			
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
		
	}
	public String updateBudget()
	{
		if(checkUserLogin())
		{
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String addBudget()
	{
		if(checkUserLogin())
		{
			CodeValues tempBudgetStatus=(CodeValues)ServiceFactory.getCodeValuesService().getRecord(userCodeID);
			budget.setBudgetStatus(tempBudgetStatus);
			User Tempuser=(User)sessionMap.get("userName");
			budget.setUser(Tempuser);
			ServiceFactory.getBudgetService().addBudget(budget);
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String viewBudgets()
	{
		if(checkUserLogin())
		{
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String deleteBudget()
	{
		if(checkUserLogin())
		{
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String editBudget()
	{
		if(checkUserLogin())
		{
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public boolean checkUserLogin()
	{
		sessionMap=(SessionMap<String,Object>)ActionContext.getContext().getSession();
		boolean isUserLoggedIn=false;
		if(sessionMap!=null && sessionMap.containsKey("userName"))
		{
			isUserLoggedIn=true;
		}
		return isUserLoggedIn;
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
