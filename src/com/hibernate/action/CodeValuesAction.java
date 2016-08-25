package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.CodeGroup;
import com.hibernate.resource.CodeValues;
import com.hibernate.service.ServiceFactory;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CodeValuesAction extends ActionSupport implements SessionAware
{
	private int groupID;
	private int selectedCodeID;
	public int getSelectedCodeID() {
		return selectedCodeID;
	}
	public void setSelectedCodeID(int selectedCodeID) {
		this.selectedCodeID = selectedCodeID;
	}
	private CodeValues codeValue=new CodeValues();
	
	private List<CodeValues> listCodeValues=new ArrayList<CodeValues>();
	private List<CodeGroup> listCodeGroups=new ArrayList<CodeGroup>();
	private SessionMap<String,Object> sessionMap;
	
	public CodeValuesAction()
	{}
	public SessionMap<String,Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String,Object> sessionMap) {
		this.sessionMap =  sessionMap;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public List<CodeValues> getListCodeValues() {
		return listCodeValues;
	}
	public void setListCodeValues(List<CodeValues> listCodeValues) {
		this.listCodeValues = listCodeValues;
	}
	public List<CodeGroup> getListCodeGroups() {
		return listCodeGroups;
	}
	public void setListCodeGroups(List<CodeGroup> listCodeGroups) {
		this.listCodeGroups = listCodeGroups;
	}
	
	public CodeValues getCodeValue() {
		return codeValue;
	}
	public void setCodeValue(CodeValues codeValue) {
		this.codeValue = codeValue;
	}
	
	public void refresh()
	{
		listCodeValues=ServiceFactory.getCodeValuesService().getRecords();
		listCodeGroups=ServiceFactory.getCodeGroupService().getActiveRecords(true);
	}
	public String init()
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
			CodeGroup codeGroup=ServiceFactory.getCodeGroupService().getRecord(groupID);
			codeValue.setCodeGroup(codeGroup);
			ServiceFactory.getCodeValuesService().addCodeValue(codeValue);
			listCodeValues=ServiceFactory.getCodeValuesService().getRecords();
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
			listCodeValues=ServiceFactory.getCodeValuesService().getRecords();
			listCodeGroups=ServiceFactory.getCodeGroupService().getRecords();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String deleteCodeValue()
	{
		if(checkUserLogin())
		{
			codeValue=ServiceFactory.getCodeValuesService().getRecord(selectedCodeID);
			ServiceFactory.getCodeValuesService().delete(codeValue);
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
			codeValue=ServiceFactory.getCodeValuesService().getRecord(selectedCodeID);
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
			codeValue.setCodeGroup((CodeGroup)ServiceFactory.getCodeGroupService().getRecord(groupID));
			ServiceFactory.getCodeValuesService().update(codeValue);
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
