package com.hibernate.dao;

import com.hibernate.dao.impl.AuthorDaoImpl;
import com.hibernate.dao.impl.BookDaoImpl;
import com.hibernate.dao.impl.BudgetDaoImpl;
import com.hibernate.dao.impl.ChapterDaoImpl;
import com.hibernate.dao.impl.CodeGroupDaoImpl;
import com.hibernate.dao.impl.CodeValuesDaoImpl;
import com.hibernate.dao.impl.ExpenseDaoImpl;
import com.hibernate.dao.impl.IncomeDaoImpl;
import com.hibernate.dao.impl.JFreeChartDaoImpl;
import com.hibernate.dao.impl.PayeeDaoImpl;
import com.hibernate.dao.impl.RecurringTransactionDaoImpl;
import com.hibernate.dao.impl.TestDaoImpl;
import com.hibernate.dao.impl.UserCodeValueDaoImpl;
import com.hibernate.dao.impl.UserDaoImpl;

public abstract class DaoFactory 
{
	private static TestDaoIF _testInstance;
	private static AuthorDaoIF _authorInstance;
	private static CodeValuesDaoIF _codeValuesInstance;
	private static CodeGroupDaoIF _codeGroupInstance;
	private static BookDaoIF _bookInstance;
	private static ChapterDaoIF _chapterInstance;
	private static UserDaoIF _userInstance;
	private static IncomeDaoIF _incomeInstance;
	private static ExpenseDaoIF _expenseInstance;
	private static UserCodeValueDaoIF _userCodeValueInstance;
	private static PayeeDaoIF _payeeInstance;
	private static RecurringTransactionDaoIF _recurringTransactionInstance;
	private static BudgetDaoIF _budgetInstance;
	private static JFreeChartDaoIF _jFreeChartInstance;
	
	public static JFreeChartDaoIF getJFreeChartInstance()
	{
		if(_jFreeChartInstance==null)
		{
			_jFreeChartInstance=new JFreeChartDaoImpl();
		}
		return _jFreeChartInstance;
	}
	
	public static BudgetDaoIF getBudgetInstance()
	{
		if(_budgetInstance==null)
		{
			synchronized (BudgetDaoIF.class) {
				if(_budgetInstance==null)
				{
					_budgetInstance = new BudgetDaoImpl();
				}
			}
			
		}
		return _budgetInstance;
	}
	
	public static RecurringTransactionDaoIF getRecurringTransactionInstance()
	{
		if(_recurringTransactionInstance == null)
		{
			_recurringTransactionInstance = new RecurringTransactionDaoImpl();
		}
		return _recurringTransactionInstance;
	}
	
	public static PayeeDaoIF getPayeeInstance()
	{
		if(_payeeInstance == null)
			_payeeInstance = new PayeeDaoImpl();
		
		return _payeeInstance;
	}
	
	public static UserCodeValueDaoIF getUserCodeValueInstance()
	{
		if(_userCodeValueInstance == null)
		{
			_userCodeValueInstance=new UserCodeValueDaoImpl();
		}
		return _userCodeValueInstance;
	}
	
	public static ExpenseDaoIF getExpenseInstance()
	{
		if(_expenseInstance == null)
		{
			_expenseInstance = new ExpenseDaoImpl();
		}
		return _expenseInstance;
	}
	
	public static IncomeDaoIF getIncomeInstance()
	{
		if(_incomeInstance == null)
			_incomeInstance=new IncomeDaoImpl();
		
		return _incomeInstance;
	}
	
	public static UserDaoIF getUserInstance()
	{
		if(_userInstance == null)
			_userInstance = new UserDaoImpl();
		return _userInstance;
	}
	public static ChapterDaoIF getChapterInstance()
	{
		if(_chapterInstance == null)
			_chapterInstance = new ChapterDaoImpl();
		
		return _chapterInstance;
	}
	
	public static BookDaoIF getBookInstance()
	{
		if(_bookInstance == null)
		{
			_bookInstance = new BookDaoImpl();
		}
		
		return _bookInstance;
	}
	public static CodeGroupDaoIF getCodeGroupInstance()
	{
		if(_codeGroupInstance == null)
		{
			synchronized (CodeGroupDaoIF.class) {
				if(_codeGroupInstance==null)
				_codeGroupInstance = new CodeGroupDaoImpl();
			}
			
		}
		return _codeGroupInstance;
	}
	
	public static CodeValuesDaoIF getCodeValuesInstance()
	{
		if(_codeValuesInstance == null)
		{
			synchronized (CodeValuesDaoIF.class) {
				if(_codeValuesInstance==null)
					_codeValuesInstance = new CodeValuesDaoImpl();
			}
			
		}
			
		return _codeValuesInstance;
	}
	public static AuthorDaoIF getAuthorInstance()
	{
		if(_authorInstance == null)
		{
			_authorInstance = new AuthorDaoImpl();
		}
		return _authorInstance;
	}
	
	public static TestDaoIF getTestInstance()
	{
		if(_testInstance == null )
		{
			_testInstance = new TestDaoImpl();
		}
		return _testInstance;
	}
}
