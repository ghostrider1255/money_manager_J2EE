package com.hibernate.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.Income;
import com.hibernate.resource.User;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyDataTable;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.constants.ConfigProperties;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DataTableIncomeAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7089913620133503120L;
	
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private int iDisplayLength;
	private int iDisplayStart;
	private String sEcho;
	private List<Income> aaData;

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

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public List<Income> getAaData() {
		return aaData;
	}

	public void setAaData(List<Income> aaData) {
		this.aaData = aaData;
	}
	
	public int getiDisplayLength() {
		return iDisplayLength;
	}

	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
		
	}
	
	public String incomeExecute()
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
			if(filterString!=null && filterString.length()>=Integer.parseInt(MyUtils.getProperty(ConfigProperties.SEARCH_FILTER_STRING_MIN_LEN)))
			{
				List<Income> allIncomeRecords=(List<Income>)ServiceFactory.getIncomeService().getRecords(tempUser);
				List<Income> filteredIncome=new ArrayList<Income>();
				int numberOfCols=5;
				for(int i=0;i<numberOfCols;i++)
				{
					boolean isSearchable_colIndex=Boolean.parseBoolean(request.getParameter("columns["+i+"][searchable]"));
					if(isSearchable_colIndex==true && allIncomeRecords!=null && allIncomeRecords.size()>0)
					{
						for(Income income:allIncomeRecords)
						{
							if(i==0)
							{
								if(income.getDescription().toUpperCase().contains(filterString.toUpperCase()))
								{
									filteredIncome.add(income);
								}
							}
							else if(i==1)
							{
								if(income.getCatergory().getUserCodeDesc().toUpperCase().contains(filterString.toUpperCase()));
								{
									filteredIncome.add(income);
								}
							}
							else if(i==2)
							{
								if(income.getPayee().getPayeeName().toUpperCase().contains(filterString.toUpperCase()));
								{
									filteredIncome.add(income);
								}
							}
							else if(i==4)
							{
								if(income.getAmount()==Double.parseDouble(filterString));
								{
									filteredIncome.add(income);
								}
							}
						}
					}
				}
				
				setAaData(filteredIncome);
				setiTotalRecords(getAaData().size());
				setiTotalDisplayRecords(getAaData().size());
			}
			else
			{
				setAaData((List<Income>)ServiceFactory.getIncomeService().getRecords(tempUser));
				setiTotalRecords(getAaData().size());
				setiTotalDisplayRecords(getAaData().size());
			}
			/* split the aaData(i.e.,List<Income>) to have the data that corresponds to the current page*/
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
							Collections.sort(getAaData(),Income.IncomeDescComparator);
						}
						else if(orderColumn==3)
						{
							Collections.sort(getAaData(),Income.IncomeDateComparator);
						}
						else if(orderColumn==4)
						{
							Collections.sort(getAaData(),Income.IncomeAmountComparator);
						}
					}
					else if(sortDirection!=null)
					{
						if(orderColumn==0)
						{
							Collections.sort(getAaData(),Income.IncomeDescComparator);
						}
						else if(orderColumn==3)
						{
							Collections.sort(getAaData(),Income.IncomeDateComparator);
						}
						else if(orderColumn==4)
						{
							Collections.sort(getAaData(),Income.IncomeAmountComparator);
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
	public String incomeEditor()
	{
		
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

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

}
