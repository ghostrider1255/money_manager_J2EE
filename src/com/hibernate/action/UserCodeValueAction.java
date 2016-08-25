package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.CodeValues;
import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.constants.MyConstants;
import com.hibernate.utils.tablescolumns.CodeValueColumn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserCodeValueAction extends ActionSupport implements SessionAware
{
	private int codeID=0;
	private int selectedUserCodeValueID=0;
	private UserCodeValue userCodeValue=new UserCodeValue();
	
	private List<UserCodeValue> listUserCodeValues=new ArrayList<UserCodeValue>();
	private List<CodeValues> listCodeValues=new ArrayList<CodeValues>();
	private SessionMap<String,Object> sessionMap;
	
	public int getCodeID() {
		return codeID;
	}

	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}

	public int getSelectedUserCodeValueID() {
		return selectedUserCodeValueID;
	}

	public void setSelectedUserCodeValueID(int selectedUserCodeValueID) {
		this.selectedUserCodeValueID = selectedUserCodeValueID;
	}

	public UserCodeValue getUserCodeValue() {
		return userCodeValue;
	}

	public void setUserCodeValue(UserCodeValue userCodeValue) {
		this.userCodeValue = userCodeValue;
	}

	public List<UserCodeValue> getListUserCodeValues() {
		return listUserCodeValues;
	}

	public void setListUserCodeValues(List<UserCodeValue> listUserCodeValues) {
		this.listUserCodeValues = listUserCodeValues;
	}

	public List<CodeValues> getListCodeValues() {
		return listCodeValues;
	}

	public void setListCodeValues(List<CodeValues> listCodeValues) {
		this.listCodeValues = listCodeValues;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	public UserCodeValueAction()
	{}
		
	public void refresh()
	{
		User tempUser=(User)sessionMap.get("userName");
		listUserCodeValues=ServiceFactory.getUserCodeValueService().getRecords(tempUser);
		listCodeValues=new ArrayList<CodeValues>();
		listCodeValues=ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP.toString(), MyConstants.USER_CATEGORY);
	}
	public String initialize()
	{
		if(checkUserLogin())
		{
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String addRecord()
	{
		if(checkUserLogin())
		{
			CodeValues tempCodeValue=ServiceFactory.getCodeValuesService().getRecord(codeID);
			userCodeValue.setCodeValues(tempCodeValue);
			User tempUser=null;
			sessionMap=(SessionMap<String,Object>)ActionContext.getContext().getSession();
			if(sessionMap!=null && sessionMap.containsKey("userName"))
			{
				tempUser=(User)sessionMap.get("userName");
				userCodeValue.setUser(tempUser);
			}
			ServiceFactory.getUserCodeValueService().addUserCodeValue(userCodeValue);
			listUserCodeValues=ServiceFactory.getUserCodeValueService().getRecords(tempUser);
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String execute()
	{
		if(checkUserLogin())
		{
			User tempUser=(User)sessionMap.get("userName");
			listUserCodeValues=ServiceFactory.getUserCodeValueService().getRecords(tempUser);
			listCodeValues=ServiceFactory.getCodeValuesService().getRecords();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	
	public String editCodeValues()
	{
		if(checkUserLogin())
		{
			userCodeValue=ServiceFactory.getUserCodeValueService().getRecord(selectedUserCodeValueID);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String updateRecord()
	{
		if(checkUserLogin())
		{
			userCodeValue.setUser((User)sessionMap.get("userName"));
			userCodeValue.setCodeValues((CodeValues)ServiceFactory.getCodeValuesService().getRecord(codeID));
			ServiceFactory.getUserCodeValueService().updateRecord(userCodeValue);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	
	public String deleteUserCodeValue()
	{
		if(checkUserLogin())
		{
			UserCodeValue userCodeValueForDeletion=ServiceFactory.getUserCodeValueService().getRecord(selectedUserCodeValueID);
			ServiceFactory.getUserCodeValueService().delete(userCodeValueForDeletion);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap=sessionMap;
		
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
