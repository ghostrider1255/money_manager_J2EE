package com.hibernate.service;

import com.hibernate.service.impl.AuthorServiceImpl;
import com.hibernate.service.impl.BookServiceImpl;
import com.hibernate.service.impl.BudgetServiceImpl;
import com.hibernate.service.impl.ChapterServiceImpl;
import com.hibernate.service.impl.CodeGroupServiceImpl;
import com.hibernate.service.impl.CodeValuesServiceImpl;
import com.hibernate.service.impl.ExpenseServiceImpl;
import com.hibernate.service.impl.IncomeServiceImpl;
import com.hibernate.service.impl.JFreeChartServiceImpl;
import com.hibernate.service.impl.PayeeServiceImpl;
import com.hibernate.service.impl.RecurringTransactionServiceImpl;
import com.hibernate.service.impl.TestServiceImpl;
import com.hibernate.service.impl.UserCodeValueServiceImpl;
import com.hibernate.service.impl.UserServiceImpl;

public abstract class ServiceFactory 
{
	private static TestServiceIF _testServiceIF;
	private static AuthorServiceIF _authorServiceIF;
	private static CodeValuesServiceIF _codeValuesServiceIF;
	private static CodeGroupServiceIF _codeGroupServiceIF;
	private static BookServiceIF _bookServiceIF;
	private static ChapterServiceIF _chapterServiceIF;
	private static UserServiceIF _userServiceIF;
	private static IncomeServiceIF _incomeServiceIF;
	private static ExpenseServiceIF _expenseServiceIF;
	private static UserCodeValueServiceIF _userCodeValueServiceIF;
	private static PayeeServiceIF _payeeServiceIF;
	private static RecurringTransactionServiceIF _recurringTransactionServiceIF;
	private static BudgetServiceIF _budgetServiceIF;
	private static JFreeChartServiceIF _jFreeChartServiceIF;
	
	public static JFreeChartServiceIF getJFreeChartService()
	{
		if(_jFreeChartServiceIF==null)
		{
			_jFreeChartServiceIF= new JFreeChartServiceImpl();
		}
		return _jFreeChartServiceIF;
	}
	
	public static BudgetServiceIF getBudgetService()
	{
		if(_budgetServiceIF == null)
		{
			_budgetServiceIF =new BudgetServiceImpl();
		}
		return _budgetServiceIF;
	}
	
	public static RecurringTransactionServiceIF getRecurringTransactionService()
	{
		if(_recurringTransactionServiceIF == null)
		{
			_recurringTransactionServiceIF= new RecurringTransactionServiceImpl();
		}
		return _recurringTransactionServiceIF;
	}
	public static PayeeServiceIF getPayeeService()
	{
		if(_payeeServiceIF == null)
		{
			_payeeServiceIF=new PayeeServiceImpl();
		}
		return _payeeServiceIF;
	}
	
	public static UserCodeValueServiceIF getUserCodeValueService()
	{
		if(_userCodeValueServiceIF == null)
		{
			_userCodeValueServiceIF= new UserCodeValueServiceImpl();
		}
		return _userCodeValueServiceIF;
	}
	public static ExpenseServiceIF getExpenseService()
	{
		if(_expenseServiceIF == null)
		{
			_expenseServiceIF = new ExpenseServiceImpl();
		}
		return _expenseServiceIF;
	}
	
	public static IncomeServiceIF getIncomeService()
	{
		if(_incomeServiceIF == null)
		{
			_incomeServiceIF = new IncomeServiceImpl();
		}
		return _incomeServiceIF;
	}
	
	public static UserServiceIF getUserService()
	{
		if(_userServiceIF == null)
		{
			_userServiceIF = new UserServiceImpl();
		}
		return _userServiceIF;
	}
	
	public static ChapterServiceIF getChapterService()
	{
		if(_chapterServiceIF == null)
		{
			_chapterServiceIF=new ChapterServiceImpl();
		}
		
		return _chapterServiceIF;
	}
	
	public static BookServiceIF getBookService()
	{
		if(_bookServiceIF == null)
		{
			_bookServiceIF = new BookServiceImpl();
		}
		return _bookServiceIF;
	}
	
	public static CodeGroupServiceIF getCodeGroupService()
	{
		if(_codeGroupServiceIF == null)
			_codeGroupServiceIF = new CodeGroupServiceImpl();
		
		return _codeGroupServiceIF;
	}
	
	public static CodeValuesServiceIF getCodeValuesService()
	{
		if(_codeValuesServiceIF == null)
			_codeValuesServiceIF = new CodeValuesServiceImpl();
		
		return _codeValuesServiceIF;
	}
	
	public static AuthorServiceIF getAuthorService()
	{
		if(_authorServiceIF == null)
			_authorServiceIF = new AuthorServiceImpl();
		
		return _authorServiceIF;
	}
	
	public static TestServiceIF getTestService()
	{
		if(_testServiceIF == null)
			_testServiceIF = new TestServiceImpl();
		
		return _testServiceIF;
	}

}
