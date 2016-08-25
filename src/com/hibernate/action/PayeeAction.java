package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.CodeValues;
import com.hibernate.resource.Payee;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.constants.MyConstants;
import com.hibernate.utils.tablescolumns.CodeValueColumn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PayeeAction extends ActionSupport implements SessionAware
{
	private int selectedPayeeID=0;
	private int selectedCategoryID=0;
	private Payee payee=new Payee();
	
	private SessionMap<String, Object> sessionMap;
	private List<Payee> payeeList=new ArrayList<Payee>();
	private List<CodeValues> categoryList=new ArrayList<CodeValues>();
	
	
	public PayeeAction()
	{}
	
	public int getSelectedPayeeID() {
		return selectedPayeeID;
	}

	public void setSelectedPayeeID(int selectedPayeeID) {
		this.selectedPayeeID = selectedPayeeID;
	}

	public int getSelectedCategoryID() {
		return selectedCategoryID;
	}

	public void setSelectedCategoryID(int selectedCategoryID) {
		this.selectedCategoryID = selectedCategoryID;
	}

	public Payee getPayee() {
		return payee;
	}

	public void setPayee(Payee payee) {
		this.payee = payee;
	}

	public List<Payee> getPayeeList() {
		return payeeList;
	}

	public void setPayeeList(List<Payee> payeeList) {
		this.payeeList = payeeList;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public List<CodeValues> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CodeValues> categoryList) {
		this.categoryList = categoryList;
	}
	
	
	public void refresh()
	{
		User tempUser=(User)sessionMap.get("userName");
		if(payee!=null)
		{
			payee=(Payee)ServiceFactory.getPayeeService().getRecord(payee.getPayeeID());
			categoryList=ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP.toString(), MyConstants.USER_CATEGORY);
			payeeList=ServiceFactory.getPayeeService().getRecords(tempUser);
		}
		else
		{
			categoryList=ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP.toString(), MyConstants.USER_CATEGORY);
			payeeList=ServiceFactory.getPayeeService().getRecords(tempUser);
		}
		
	}
	public String editPayee()
	{
		if(checkUserLogin())
		{
			payee=ServiceFactory.getPayeeService().getRecord(selectedPayeeID);
			User tempUser=(User)sessionMap.get("userName");
			payee.setUser(tempUser);
			categoryList=ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP.toString(), MyConstants.USER_CATEGORY);
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String updatePayee()
	{
		if(checkUserLogin())
		{
			payee.setPayeeCateogry(ServiceFactory.getCodeValuesService().getRecord(selectedCategoryID));
			User tempUser=(User)sessionMap.get("userName");
			payee.setUser(tempUser);
			ServiceFactory.getPayeeService().updateRecord(payee);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String deletePayee()
	{
		if(checkUserLogin())
		{
			Payee tempPayee=ServiceFactory.getPayeeService().getRecord(selectedPayeeID);
			ServiceFactory.getPayeeService().delete(tempPayee);
			refresh();
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String addPayee()
	{
		if(checkUserLogin())
		{
			CodeValues presentCodeValue=(CodeValues)ServiceFactory.getCodeValuesService().getRecord(selectedCategoryID);
			payee.setPayeeCateogry(presentCodeValue);
			User tempUser=(User)sessionMap.get("userName");
			payee.setUser(tempUser);
			ServiceFactory.getPayeeService().addPayee(payee);
			
			categoryList=ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP.toString(), MyConstants.USER_CATEGORY);
			payeeList=ServiceFactory.getPayeeService().getRecords(tempUser);
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String viewPayees()
	{
		if(checkUserLogin())
		{
			User tempUser=(User)sessionMap.get("userName");
			//payee.setUser(tempUser);
			payeeList=ServiceFactory.getPayeeService().getRecords(tempUser);
			return SUCCESS;
		}
		else
		{
			return LOGIN;
		}
	}
	public String execute()
	{
		sessionMap=(SessionMap<String,Object>)ActionContext.getContext().getSession();
		User tempUser=(User)sessionMap.get("userName");
		if(checkUserLogin())
		{
			categoryList=ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP.toString(), MyConstants.USER_CATEGORY);
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

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
		
	}
}
