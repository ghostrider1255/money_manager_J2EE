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
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.MyDataTable;
import com.hibernate.utils.MyUtils;
import com.hibernate.utils.constants.ConfigProperties;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 

public class DataTableExpenseAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4546323294040317496L;
	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private int iDisplayStart;
	private String sEcho;
	private List<Expense> aaData;

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

	public List<Expense> getAaData() {
		return aaData;
	}

	public void setAaData(List<Expense> aaData) {
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
	public String expenseExecute()
	{		
		try
		{
			if(checkUserLogin())
			{
				HttpServletRequest request=ServletActionContext.getRequest();
				MyDataTable dataTable=MyDataTable.getInstance(request);
				User tempUser=(User)sessionMap.get("userName");
				
				
				/* Filtering data depending on search value of dataTable*/
				String filterString=dataTable.getsSearch();
				if(filterString!=null && filterString.length()>=Integer.parseInt(MyUtils.getProperty(ConfigProperties.SEARCH_FILTER_STRING_MIN_LEN)))
				{
					List<Expense> fullExpenses=(List<Expense>)ServiceFactory.getExpenseService().getRecords(tempUser);
					List<Expense> filteredExpenses=new ArrayList<Expense>();
					int numberOfCols=5;
					for(int i=0;i<numberOfCols;i++)
					{
						boolean isSearchable_colIndex=Boolean.parseBoolean(request.getParameter("columns["+i+"][searchable]"));
						if(isSearchable_colIndex==true && fullExpenses!=null && fullExpenses.size()>0)
						{
							for(Expense expense:fullExpenses)
							{
								if(i==0)
								{
									if(expense.getDescription().toUpperCase().contains(filterString.toUpperCase()))
									{
										filteredExpenses.add(expense);
									}
								}
								else if(i==1)
								{
									if(expense.getCatergory().getUserCodeDesc().toUpperCase().contains(filterString.toUpperCase()));
									{
										filteredExpenses.add(expense);
									}
								}
								else if(i==2)
								{
									if(expense.getPayee().getPayeeName().toUpperCase().contains(filterString.toUpperCase()));
									{
										filteredExpenses.add(expense);
									}
								}
								else if(i==4)
								{
									if(expense.getAmount()==Double.parseDouble(filterString));
									{
										filteredExpenses.add(expense);
									}
								}
							}
						}
					}
					
					setAaData(filteredExpenses);
					setiTotalRecords(getAaData().size());
					setiTotalDisplayRecords(getAaData().size());
				}
				else
				{
					setAaData((List<Expense>)ServiceFactory.getExpenseService().getRecords(tempUser));
					setiTotalRecords(getAaData().size());
					setiTotalDisplayRecords(getAaData().size());
				}
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
				int orderColumn=5;
				
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
								Collections.sort(getAaData(),new Comparator<Expense>() {
							        @Override
							        public int compare(final Expense object1, final Expense object2) {
							            return object1.getDescription().compareTo(object2.getDescription());
							        }
							       } );
							}
							else if(orderColumn==4)
							{
								Collections.sort(getAaData(),Expense.ExpenseAmountComparator);
							}
							Collections.reverse(getAaData());
						}
						else if(sortDirection!=null)
						{
							if(orderColumn==0)
							{
								Collections.sort(getAaData(),new Comparator<Expense>() {
							        @Override
							        public int compare(final Expense object1, final Expense object2) {
							            return object1.getDescription().compareTo(object2.getDescription());
							        }
							       } );
							}
							else if(orderColumn==4)
							{
								Collections.sort(getAaData(),Expense.ExpenseAmountComparator);
							}
						}
					}
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

	public int getiDisplayStart() {
		return iDisplayStart;
	}

	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
}
