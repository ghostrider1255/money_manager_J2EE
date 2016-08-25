package com.hibernate.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.hibernate.resource.CodeValues;
import com.hibernate.resource.Expense;
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

public class UserExpenseAction extends ActionSupport implements SessionAware
{

	private SessionMap<String, Object> sessionMap;
	private Expense expense;
	private int expenseID;
	private int userCodeID;
	private int selectedPayeeID;
	
	private String recurringOrOneTime;
	private String occureneType;
	private String noOfOccurence;
	private String transactionEndDate;
	private String transactionDate;
	
	private User user;
	
	private List<CodeValues> transactionOccurenceTypeList;
	private List<Expense> expenseList;
	private List<UserCodeValue> categoryList=new ArrayList<UserCodeValue>();
	private List<Payee> payeesList=new ArrayList<Payee>();
	
	public int getSelectedPayeeID() {
		return selectedPayeeID;
	}
	public void setSelectedPayeeID(int selectedPayeeID) {
		this.selectedPayeeID = selectedPayeeID;
	}
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	public int getExpenseID() {
		return expenseID;
	}
	public void setExpenseID(int expenseID) {
		this.expenseID = expenseID;
	}
	public int getUserCodeID() {
		return userCodeID;
	}
	public void setUserCodeID(int userCodeID) {
		this.userCodeID = userCodeID;
	}
	public List<Expense> getExpenseList() {
		return expenseList;
	}
	public void setExpenseList(List<Expense> expenseList) {
		this.expenseList = expenseList;
	}
	public List<UserCodeValue> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<UserCodeValue> categoryList) {
		this.categoryList = categoryList;
	}
	public SessionMap<String, Object> getSessionMap() {
		return sessionMap;
	}
	public void setSessionMap(SessionMap<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
	}
	public List<Payee> getPayeesList() {
		return payeesList;
	}
	public void setPayeesList(List<Payee> payeesList) {
		this.payeesList = payeesList;
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
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.setSessionMap((SessionMap<String,Object>)sessionMap);
	}
	
	public void nullifyExpense()
	{
		expense=null;
		expenseID=0;
		userCodeID=0;
		selectedPayeeID=0;
		transactionDate="";
		transactionEndDate="";
		recurringOrOneTime="1";
	}
	public void refresh()
	{
		User tempUser=(User)sessionMap.get("userName");
		if(expense!=null)
		{
			if(expense.getExpenseID()!=0)
			{
				expense=(Expense)ServiceFactory.getExpenseService().getRecord(expense.getExpenseID());
			}
			
			expenseList=(List<Expense>)ServiceFactory.getExpenseService().getRecords(expense.getUser());
			categoryList=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().
					getRecords(tempUser,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.EXPENSE_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(tempUser, PayeeColumn.PAYEE_CATEGORY, MyConstants.EXPENSE_CATEGORY);
		}
		else
		{
			expenseList=(List<Expense>)ServiceFactory.getExpenseService().getRecords(tempUser);
			categoryList=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().
					getRecords(tempUser,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.EXPENSE_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(tempUser, PayeeColumn.PAYEE_CATEGORY, MyConstants.EXPENSE_CATEGORY);
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
					getRecords(tempUser,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.EXPENSE_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(tempUser, PayeeColumn.PAYEE_CATEGORY, MyConstants.EXPENSE_CATEGORY);
		}
		else
		{
			
			categoryList=(List<UserCodeValue>)ServiceFactory.getUserCodeValueService().
					getRecords(user,UserCodeValueColumn.USER_CATEGORY.toString(),MyConstants.EXPENSE_CATEGORY.toString());
			payeesList=(List<Payee>)ServiceFactory.getPayeeService().getRecords(user, PayeeColumn.PAYEE_CATEGORY, MyConstants.EXPENSE_CATEGORY);
		}
		
		refresh();
		expense=null;
		return SUCCESS;
	}
	public String addExpense()
	{

		try
		{
			UserCodeValue tempCategory=(UserCodeValue)ServiceFactory.getUserCodeValueService().getRecord(userCodeID);
			expense.setCatergory(tempCategory);
			User tempUser=(User)sessionMap.get("userName");
			expense.setUser(tempUser);
			Payee tempPayee=(Payee)ServiceFactory.getPayeeService().getRecord(selectedPayeeID);
			expense.setPayee(tempPayee);
			
			expense.setTransactionDate(transactionDate);
			ServiceFactory.getExpenseService().addExpense(expense);
			ServiceFactory.getBudgetService().updateBudget(expense.getUser(), expense);
			nullifyExpense();	
			refresh();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return SUCCESS;
	
	}
	public RecurringTransaction updateWithExpenseDetails(RecurringTransaction recurringTransaction,Expense expense)
	{
		try
		{
			recurringTransaction.setAmount(expense.getAmount());
			recurringTransaction.setDescription(expense.getDescription());
			recurringTransaction.setIncome(false);
			recurringTransaction.setExpense(true);
			recurringTransaction.setCatergory(expense.getCatergory());
			recurringTransaction.setPayee(expense.getPayee());
			
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
			recurringTransaction.setUser(expense.getUser());
		    recurringTransaction.setNoOfoccurenses(Integer.parseInt(noOfOccurence));
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		
		return recurringTransaction;
	}
	public String viewUserExpense()
	{
		refresh();
		return SUCCESS;
	}
	public String deleteUserExpense()
	{
		Expense tempUserExpenseForDeletion=ServiceFactory.getExpenseService().getRecord(expenseID);
		ServiceFactory.getExpenseService().delete(tempUserExpenseForDeletion);
		ServiceFactory.getBudgetService().updateBudget(tempUserExpenseForDeletion.getUser(), tempUserExpenseForDeletion);
		refresh();
		return SUCCESS;
	}
	public String updateExpense()
	{
		expense.setCatergory(ServiceFactory.getUserCodeValueService().getRecord(userCodeID));
		expense.setPayee(ServiceFactory.getPayeeService().getRecord(selectedPayeeID));
		User tempUser=(User)sessionMap.get("userName");
		expense.setUser(tempUser);
		ServiceFactory.getExpenseService().updateRecord(expense);
		ServiceFactory.getBudgetService().updateBudget(expense.getUser(), expense);
		refresh();
		return SUCCESS;
	}
	public String editExpense()
	{
		expense=ServiceFactory.getExpenseService().getRecord(expenseID);
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
	public List<CodeValues> getTransactionOccurenceTypeList() {
		return transactionOccurenceTypeList;
	}
	public void setTransactionOccurenceTypeList(
			List<CodeValues> transactionOccurenceTypeList) {
		this.transactionOccurenceTypeList = transactionOccurenceTypeList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
