package com.hibernate.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.Budget;
import com.hibernate.resource.Income;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyDataTable;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.constants.ConfigProperties;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataTableBudgetAction extends ActionSupport implements SessionAware
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5239938537770079233L;
	
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private int iDisplayLength;
	private int iDisplayStart;
	private String sEcho;
	private List<Budget> aaData;
	
	private SessionMap<String, Object> sessionMap;

	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

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

	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
	}

	public List<Budget> getAaData() {
		return aaData;
	}

	public void setAaData(List<Budget> aaData) {
		this.aaData = aaData;
	}
	
	public String refreshBudgetData()
	{
		try
		{
			
			HttpServletRequest request=ServletActionContext.getRequest();
			MyDataTable dataTable=MyDataTable.getInstance(request);
			if(sessionMap==null)
			{
				sessionMap=fetchSessionMap();
			}
			User tempUser=(User)sessionMap.get("userName");
			
			
			/* Filtering data depending on search value of dataTable*/
			String filterString=dataTable.getsSearch();

			List<Budget> allBudgetRecords=(List<Budget>)ServiceFactory.getBudgetService().getRecords(tempUser);
			//allBudgetRecords=(List<Budget>)ServiceFactory.getBudgetService().getDataTablerecords(allBudgetRecords);
			List<Budget> filteredBudget=new ArrayList<Budget>();
			int numberOfCols=5;
			for(int i=0;i<numberOfCols;i++)
			{
				boolean isSearchable_colIndex=Boolean.parseBoolean(request.getParameter("columns["+i+"][searchable]"));
				if(isSearchable_colIndex==true && allBudgetRecords!=null && allBudgetRecords.size()>0)
				{
					for(Budget budget:allBudgetRecords)
					{
						if(i==0)
						{
							if(budget.getBudgetStartDate().toUpperCase().contains(filterString.toUpperCase()))
							{
								filteredBudget.add(budget);
							}
						}
						else if(i==1)
						{
							if(budget.getBudgetEndDate().toUpperCase().contains(filterString.toUpperCase()));
							{
								filteredBudget.add(budget);
							}
						}
						else if(i==2)
						{
							if(budget.getIncomeAmount()==Double.parseDouble(filterString));
							{
								filteredBudget.add(budget);
							}
						}
						else if(i==4)
						{
							if(budget.getExpenseAmount()==Double.parseDouble(filterString));
							{
								filteredBudget.add(budget);
							}
						}
					}
				}
			}
			
			setAaData(filteredBudget);
			setiTotalRecords(getAaData().size());
			setiTotalDisplayRecords(getAaData().size());
		
			/* split the aaData(i.e.,List<Budget>) to have the data that corresponds to the current page*/
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
			/* Sorting of data depending upon the sort Action performed on the respective columns*/
			int orderColumn=0;
			
			if(getAaData()!=null && getAaData().size()>0)
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
							Collections.sort(getAaData(),Budget.BudgetDateComparator);
						}
						else if(orderColumn==3)
						{
							Collections.sort(getAaData(),Budget.BudgetIncomeAmountComparator);
						}
						else if(orderColumn==4)
						{
							Collections.sort(getAaData(),Budget.BudgetExpenseAmountComparator);
						}
					}
					else if(sortDirection!=null)
					{
						if(orderColumn==0)
						{
							Collections.sort(getAaData(),Budget.BudgetDateComparator);
						}
						else if(orderColumn==3)
						{
							Collections.sort(getAaData(),Budget.BudgetIncomeAmountComparator);
						}
						else if(orderColumn==4)
						{
							Collections.sort(getAaData(),Budget.BudgetExpenseAmountComparator);
						}
						Collections.reverse(getAaData());
					}
				}
			}
			
			return SUCCESS;
		
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return SUCCESS;
		}
	
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
