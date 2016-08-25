package com.hibernate.action;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.Budget;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3714027884228564085L;


	private SessionMap<String,Object> sessionMap;
	

	private double userID=0;
	private User user;
	private String isUserNameAlreadyAvailable="yes";
	private Budget budget;
	private int budgetProgress;
	
	
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
	public double getUserID() {
		return userID;
	}
	public void setUserID(double userID) {
		this.userID = userID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String,Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public String getIsUserNameAlreadyAvailable() {
		return isUserNameAlreadyAvailable;
	}
	public void setIsUserNameAlreadyAvailable(String isUserNameAlreadyAvailable) {
		this.isUserNameAlreadyAvailable = isUserNameAlreadyAvailable;
	}
	
	public String execute()
	{
		return SUCCESS;
	}
	
	public String registerUser()
	{
		ServiceFactory.getUserService().addUser(user);
		sessionMap.put("userName", user);
	
		return SUCCESS;
	}
	public String logoutUser()
	{
		if(sessionMap!=null && sessionMap.containsKey("userName"))
		{
			sessionMap.remove("userName");
			ActionContext.getContext().setSession(sessionMap);
		}
		return LOGIN;
	}
	public String loginUser()
	{
		if(user==null && sessionMap.containsKey("userName"))
		{
			user=(User)sessionMap.get("userName");
		}
			
		User validUser=ServiceFactory.getUserService().validateUser(user);
		
		if(validUser!=null)
		{
			sessionMap.put("userName", validUser);
			user=validUser;
			
			budget=ServiceFactory.getBudgetService().getBudget(validUser, MyUtils.convertDateToString(new Date(), "dd-MM-yyyy"));
			budgetProgress=ServiceFactory.getBudgetService().getBudgetPercentage(budget);
			
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
		
	}
	
	public String isUserNameAvailable()
	{
		boolean suceess=ServiceFactory.getUserService().isUserNameAvailable(user);
		if(suceess)
		{
			isUserNameAlreadyAvailable="yes";
		}
		else
		{
			isUserNameAlreadyAvailable="no";
		}
		return SUCCESS;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap=(SessionMap<String,Object>)sessionMap;
		
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
}
