package com.hibernate.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.Expense;
import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyDataTable;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.constants.ConfigProperties;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataTableCategoryAction extends ActionSupport implements SessionAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4356794473023196710L;

	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private int iDisplayStart;
	private String sEcho;
	private List<UserCodeValue> aaData;

	private SessionMap<String, Object> sessionMap;
	
	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
		
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public List<UserCodeValue> getAaData() {
		return aaData;
	}

	public void setAaData(List<UserCodeValue> aaData) {
		this.aaData = aaData;
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
	public String refreshCategoryData()
	{		
		try
		{
			if(checkUserLogin())
			{
				HttpServletRequest request=ServletActionContext.getRequest();
				MyDataTable dataTable=MyDataTable.getInstance(request);
				User tempUser=(User)sessionMap.get("userName");
				
				
				/* Filtering data depending on search value of dataTable*/
				

				List<UserCodeValue> fullCategories=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().getRecords(tempUser);
				List<UserCodeValue> filteredCategories=new ArrayList<UserCodeValue>();
				int numberOfCols=3;
				for(int i=0;i<numberOfCols;i++)
				{
					boolean isSearchable_colIndex=Boolean.parseBoolean(request.getParameter("columns["+i+"][searchable]"));
					String filterString=request.getParameter("search[value]");
					if(isSearchable_colIndex==true && fullCategories!=null && fullCategories.size()>0 && filterString!=null && filterString.trim().length()>0)
					{
						for(UserCodeValue category:fullCategories)
						{
							if(i==0)
							{
								if(category.getUserCodeName().toUpperCase().contains(filterString.toUpperCase()))
								{
									filteredCategories.add(category);
								}
							}
							else if(i==1)
							{
								if(category.getUserCodeDesc().toUpperCase().contains(filterString.toUpperCase()));
								{
									filteredCategories.add(category);
								}
							}
							else if(i==2)
							{
								if(category.getCodeValues().getCodeDesc().toUpperCase().contains(filterString.toUpperCase()));
								{
									filteredCategories.add(category);
								}
							}
						}
					}
				}
				
				
				/* Sorting of data depending upon the sort Action performed on the respective columns*/
				int orderColumn=3;
				if(filteredCategories!=null && filteredCategories.size()==0)
				{
					filteredCategories=fullCategories;
				}
				
				if(filteredCategories!=null && filteredCategories.size()>0)
				{
					String orderCol=request.getParameter("order[0][column]");
					if(orderCol!=null)
					{
						orderColumn=Integer.parseInt(orderCol);
						String sortDirection=request.getParameter("order[0][dir]");
						if(sortDirection!=null && sortDirection.equals("asc"))
						{
							if(orderColumn==0)
							{
								Collections.sort(filteredCategories,UserCodeValue.UserCodeValueNameComparator);
							}
							else if(orderColumn==4)
							{
								Collections.sort(filteredCategories,UserCodeValue.UserCodeValueTypeDescComparator);
							}
							Collections.reverse(filteredCategories);
						}
						else if(sortDirection!=null)
						{
							if(orderColumn==0)
							{
								Collections.sort(filteredCategories,UserCodeValue.UserCodeValueNameComparator);
							}
							else if(orderColumn==4)
							{
								Collections.sort(filteredCategories,UserCodeValue.UserCodeValueTypeDescComparator);
							}
						}
					}
				}
				
				setAaData(filteredCategories);
				setiTotalRecords(getAaData().size());
				setiTotalDisplayRecords(getAaData().size());
			
				if(getiTotalDisplayRecords()>0 )
				{
					int fromIndex=0,toIndex=0;
					if(dataTable.getLength()>0)
					{
						fromIndex=(int)(Math.ceil((dataTable.getStart()+1)/dataTable.getLength())*dataTable.getLength());
						toIndex=fromIndex+dataTable.getLength();
					}
					else
					{
						fromIndex=(int)(Math.ceil((dataTable.getStart()+1)/dataTable.getiDisplayLength())*dataTable.getiDisplayLength());
						toIndex=fromIndex+dataTable.getiDisplayLength();
					}
					if(toIndex>getAaData().size())
					{
						toIndex=getAaData().size();
					}
					setAaData(getAaData().subList(fromIndex,toIndex));
					
				}
			
				return SUCCESS;
			}
			else
			{
				return LOGIN;
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return SUCCESS;
		}
	}
}
