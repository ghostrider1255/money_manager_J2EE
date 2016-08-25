package com.hibernate.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.CodeValues;
import com.hibernate.resource.Income;
import com.hibernate.resource.Payee;
import com.hibernate.resource.RecurringTransaction;
import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.constants.MyConstants;
import com.hibernate.utils.tablescolumns.CodeValueColumn;
import com.hibernate.utils.tablescolumns.PayeeColumn;
import com.hibernate.utils.tablescolumns.UserCodeValueColumn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserIncomeAction extends ActionSupport implements SessionAware
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8428996345275563160L;
	private SessionMap<String, Object> sessionMap;
	private Income income;
	private int incomeID;
	private int userCodeID;
	private int selectedPayeeID=0;
	
	private String recurringOrOneTime;
	private String occureneType;
	private String noOfOccurence;
	private String transactionEndDate;
	private String transactionDate;
	
	private User user;
	
	private List<CodeValues> transactionOccurenceTypeList;
	private List<Income> incomesList;
	private List<UserCodeValue> categoryList=new ArrayList<UserCodeValue>();
	private List<Payee> payeesList=new ArrayList<Payee>();
	
	private String sSearch;

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
		
	}
	public List<CodeValues> getTransactionOccurenceTypeList() {
		return transactionOccurenceTypeList;
	}
	public void setTransactionOccurenceTypeList(
			List<CodeValues> transactionOccurenceTypeList) {
		this.transactionOccurenceTypeList = transactionOccurenceTypeList;
	}
	public String getRecurringOrOneTime() {
		return recurringOrOneTime;
	}
	public void setRecurringOrOneTime(String recurringOrOneTime) {
		this.recurringOrOneTime = recurringOrOneTime;
	}
	public String getOccureneType() {
		return occureneType;
	}
	public void setOccureneType(String occureneType) {
		this.occureneType = occureneType;
	}
	public String getNoOfOccurence() {
		return noOfOccurence;
	}
	public void setNoOfOccurence(String noOfOccurence) {
		this.noOfOccurence = noOfOccurence;
	}
	public String getTransactionEndDate() {
		return transactionEndDate;
	}
	public void setTransactionEndDate(String transactionEndDate) {
		this.transactionEndDate = transactionEndDate;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public Income getIncome() {
		return income;
	}
	public void setIncome(Income income) {
		this.income = income;
	}
	public List<Income> getIncomesList() {
		return incomesList;
	}
	public void setIncomesList(List<Income> incomesList) {
		this.incomesList = incomesList;
	}
	
	public List<UserCodeValue> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<UserCodeValue> categoryList) {
		this.categoryList = categoryList;
	}
	public int getUserCodeID() {
		return userCodeID;
	}
	public void setUserCodeID(int userCodeID) {
		this.userCodeID = userCodeID;
	}	
	public int getIncomeID() {
		return incomeID;
	}
	public void setIncomeID(int incomeID) {
		this.incomeID = incomeID;
	}
	public List<Payee> getPayeesList() {
		return payeesList;
	}
	public void setPayeesList(List<Payee> payeesList) {
		this.payeesList = payeesList;
	}
	public int getSelectedPayeeID() {
		return selectedPayeeID;
	}
	public void setSelectedPayeeID(int selectedPayeeID) {
		this.selectedPayeeID = selectedPayeeID;
	}
	public void nullifyIncome()
	{
		income=null;
		incomeID=0;
		userCodeID=0;
		selectedPayeeID=0;
		transactionDate="";
		transactionEndDate="";
		recurringOrOneTime="1";
	}
	public void refresh()
	{
		User tempUser=(User)sessionMap.get("userName");
		if(income!=null)
		{
			if(income.getIncomeID()!=0)
			{
				income=(Income)ServiceFactory.getIncomeService().getRecord(income.getIncomeID());
			}
			
			
			incomesList=(List<Income>)ServiceFactory.getIncomeService().getRecords(income.getUser());
			categoryList=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().
					getRecords(tempUser,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.INCOME_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(tempUser, PayeeColumn.PAYEE_CATEGORY, MyConstants.INCOME_CATEGORY);
		}
		else
		{
			incomesList=(List<Income>)ServiceFactory.getIncomeService().getRecords(tempUser);
			categoryList=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().
					getRecords(tempUser,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.INCOME_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(tempUser, PayeeColumn.PAYEE_CATEGORY, MyConstants.INCOME_CATEGORY);
		}
		
	}
	
	public String initialize()
	{
		if(user==null)
		{
			if(sessionMap==null)
			{
				sessionMap=fetchSessionMap();
			}
			User tempUser=(User)sessionMap.get("userName");
			categoryList=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().
					getRecords(tempUser,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.INCOME_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(tempUser, PayeeColumn.PAYEE_CATEGORY, MyConstants.INCOME_CATEGORY);
		}
		else
		{
			categoryList=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().
					getRecords(user,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.INCOME_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(user, PayeeColumn.PAYEE_CATEGORY, MyConstants.INCOME_CATEGORY);
		}
		
		refresh();
		income=null;
		return SUCCESS;
	
	}
	public String addIncome()
	{
		try
		{
			UserCodeValue tempCategory=(UserCodeValue)ServiceFactory.getUserCodeValueService().getRecord(userCodeID);
			income.setCatergory(tempCategory);
			if(sessionMap==null)
			{
				sessionMap=fetchSessionMap();
			}
			User tempUser=(User)sessionMap.get("userName");
			income.setUser(tempUser);
			Payee tempPayee=(Payee)ServiceFactory.getPayeeService().getRecord(selectedPayeeID);
			income.setPayee(tempPayee);
			
			income.setTransactionDate(transactionDate);
			ServiceFactory.getIncomeService().addIncome(income);
			ServiceFactory.getBudgetService().updateBudget(income.getUser(), income);
			nullifyIncome();				
			refresh();
			
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return SUCCESS;
	}
	public RecurringTransaction updateWithIncomeDetails(RecurringTransaction recurringTransaction,Income income)
	{
		//RecurringTransaction recurringObj=new RecurringTransaction();
		try
		{
			recurringTransaction.setAmount(income.getAmount());
			recurringTransaction.setDescription(income.getDescription());
			recurringTransaction.setIncome(true);
			recurringTransaction.setExpense(false);
			recurringTransaction.setCatergory(income.getCatergory());
			recurringTransaction.setPayee(income.getPayee());
			
			transactionOccurenceTypeList=ServiceFactory.getCodeValuesService().getRecords(CodeValueColumn.CODE_GROUP.toString(), MyConstants.TRANS_OCCURENCE_TYPE);
			if(transactionOccurenceTypeList!=null && transactionOccurenceTypeList.size()>0)
			{
				switch(occureneType)
				{
					case "0":for(CodeValues tempCodeValue:transactionOccurenceTypeList)
							{
								if(tempCodeValue.getCodeName().compareTo(MyConstants.TRANSACTION_DAILY)==0)
								{
									recurringTransaction.setRecurringTransactionType(tempCodeValue);
									break;
								}
							}
							break;
							
					case "1":for(CodeValues tempCodeValue:transactionOccurenceTypeList)
							{
								if(tempCodeValue.getCodeName().compareTo(MyConstants.TRANSACTION_WEEKLY)==0)
								{
									recurringTransaction.setRecurringTransactionType(tempCodeValue);
									break;
								}
							}
							break;
					case "2":for(CodeValues tempCodeValue:transactionOccurenceTypeList)
							{
								if(tempCodeValue.getCodeName().compareTo(MyConstants.TRANSACTION_MONTHLY)==0)
								{
									recurringTransaction.setRecurringTransactionType(tempCodeValue);
									break;
								}
							}
							break;	
					case "3":for(CodeValues tempCodeValue:transactionOccurenceTypeList)
							{
								if(tempCodeValue.getCodeName().compareTo(MyConstants.TRANSACTION_YEARLY)==0)
								{
									recurringTransaction.setRecurringTransactionType(tempCodeValue);
									break;
								}
							}
							break;	
				}
			}
			else
			{
				
			}
			
			recurringTransaction.setTransactionDate(transactionDate);
			recurringTransaction.setTranssactionEndDate(transactionEndDate);
			recurringTransaction.setUser(income.getUser());
		    recurringTransaction.setNoOfoccurenses(Integer.parseInt(noOfOccurence));
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return recurringTransaction;
	}
	public String viewUserIncomes()
	{
		refresh();
		return SUCCESS;
	}
	public String deleteUserIncomes()
	{
		Income tempUserIncomeForDeletion=ServiceFactory.getIncomeService().getRecord(incomeID);
		ServiceFactory.getIncomeService().delete(tempUserIncomeForDeletion);
		ServiceFactory.getBudgetService().updateBudget(tempUserIncomeForDeletion.getUser(), tempUserIncomeForDeletion);
		refresh();
		return SUCCESS;
	}
	public String updateIncome()
	{
		income.setCatergory(ServiceFactory.getUserCodeValueService().getRecord(userCodeID));
		income.setPayee(ServiceFactory.getPayeeService().getRecord(selectedPayeeID));
		if(sessionMap==null)
		{
			sessionMap=fetchSessionMap();
		}
		User tempUser=(User)sessionMap.get("userName");
		income.setUser(tempUser);
		ServiceFactory.getIncomeService().updateRecord(income);
		ServiceFactory.getBudgetService().updateBudget(income.getUser(), income);
		refresh();
		return SUCCESS;
	}
	public String editIncome()
	{
		income=ServiceFactory.getIncomeService().getRecord(incomeID);
		refresh();
		return SUCCESS;
	}
	public String dataRefresh()
	{
		refresh();
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
	
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
