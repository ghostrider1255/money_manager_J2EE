package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.CodeGroup;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.constants.MyConstants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CodeGroupAction extends ActionSupport implements SessionAware
{
	private List<CodeGroup> codeGroupList=new ArrayList<CodeGroup>();
	private CodeGroup codeGroup = new CodeGroup();;
	private String selectedGroupID;
	private SessionMap<String,Object> sessionMap;
	

	public CodeGroupAction(){}
	public SessionMap<String,Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String,Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public List<CodeGroup> getCodeGroupList() 
	{
		return codeGroupList;
	}
	public void setCodeGroupList(List<CodeGroup> codeGroupList) 
	{
		this.codeGroupList = codeGroupList;
	}
	public CodeGroup getCodeGroup() 
	{
		return codeGroup;
	}
	public void setCodeGroup(CodeGroup codeGroup) 
	{
		this.codeGroup = codeGroup;
	}
	public String getSelectedGroupID() {
		return selectedGroupID;
	}
	public void setSelectedGroupID(String selectedGroupID) {
		this.selectedGroupID = selectedGroupID;
	}
	
	public void refresh()
	{
		codeGroupList=ServiceFactory.getCodeGroupService().getRecords();
	}
	
	public String execute()
	{
		if(checkUserLogin())
		{
			if(codeGroup==null)
			{
				codeGroup=new CodeGroup();
			}
			codeGroup.setGroupStatus(true);
			ServiceFactory.getCodeGroupService().addGroup(codeGroup);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
		
	}
	
	public String getRecords()
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
	
	public String deleteCodeGroup()
	{
		if(checkUserLogin())
		{
			codeGroup=ServiceFactory.getCodeGroupService().getRecord(Integer.parseInt(selectedGroupID));
			ServiceFactory.getCodeGroupService().delete(codeGroup);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String editCodeGroup()
	{
		if(checkUserLogin())
		{
			codeGroup=ServiceFactory.getCodeGroupService().getRecord(Integer.parseInt(selectedGroupID));
			//refresh();
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
			String returnString=SUCCESS;
			ServiceFactory.getCodeGroupService().updateRecord(codeGroup);
			refresh();
			return returnString;
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
		else
		{
			
			isUserLoggedIn=false;
		}
		return isUserLoggedIn;
	}
}
