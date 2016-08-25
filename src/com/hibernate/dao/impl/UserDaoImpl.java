package com.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.hibernate.dao.UserDaoIF;
import com.hibernate.resource.Book;
import com.hibernate.resource.CodeGroup;
import com.hibernate.resource.CodeValues;
import com.hibernate.resource.Payee;
import com.hibernate.resource.User;
import com.hibernate.resource.UserCodeValue;
import com.hibernate.service.ServiceFactory;
import com.hibernate.utils.constants.MyConstants;
import com.hibernate.utils.dbutil.HibernateUtil;
import com.hibernate.utils.tablescolumns.CodeGroupColumn;
import com.hibernate.utils.tablescolumns.UserColumns;

public class UserDaoImpl implements UserDaoIF{

	public void addDefaultEntries(User user)
	{
		List<CodeGroup> codeGroupList=ServiceFactory.getCodeGroupService().getRecords();
		
		if(codeGroupList==null || codeGroupList.size()==0)
		{
			// User Categories
			CodeGroup tempCodeGroup=new CodeGroup();
			tempCodeGroup.setGroupCode("USER_CATEGORY");
			tempCodeGroup.setGroupDesc("User Category");
			tempCodeGroup.setGroupStatus(true);
			ServiceFactory.getCodeGroupService().addGroup(tempCodeGroup);
			CodeValues incomeCategory=new CodeValues();
			incomeCategory.setCodeName("INCOME_CATEGORY");
			incomeCategory.setCodeDesc("Income Category");
			incomeCategory.setCodeGroup(tempCodeGroup);
			ServiceFactory.getCodeValuesService().addCodeValue(incomeCategory);
			CodeValues expenseCategory=new CodeValues();
			expenseCategory.setCodeName("EXPENSE_CATEGORY");
			expenseCategory.setCodeDesc("Expense Category");
			expenseCategory.setCodeGroup(tempCodeGroup);
			ServiceFactory.getCodeValuesService().addCodeValue(expenseCategory);
			
			
			// Transaction Occurence Types(Daily , Weekly , Monthly , yearly
			CodeGroup tempTransactionType=new CodeGroup();
			tempTransactionType.setGroupCode("TRANS_OCCURENCE_TYPE");
			tempTransactionType.setGroupDesc("TRANS_OCCURENCE_TYPE");
			tempTransactionType.setGroupStatus(true);
			ServiceFactory.getCodeGroupService().addGroup(tempTransactionType);
			CodeValues dailyOne=new CodeValues();
			dailyOne.setCodeName("DAILY");
			dailyOne.setCodeDesc("Daily Once");
			dailyOne.setCodeGroup(tempTransactionType);
			ServiceFactory.getCodeValuesService().addCodeValue(dailyOne);
			CodeValues weeklyOne=new CodeValues();
			weeklyOne.setCodeName("WEEKLY");
			weeklyOne.setCodeDesc("Weekly Once");
			weeklyOne.setCodeGroup(tempTransactionType);
			ServiceFactory.getCodeValuesService().addCodeValue(weeklyOne);
			CodeValues monthlyOnce=new CodeValues();
			monthlyOnce.setCodeName("MONTHLY");
			monthlyOnce.setCodeDesc("Monthly Once");
			monthlyOnce.setCodeGroup(tempTransactionType);
			ServiceFactory.getCodeValuesService().addCodeValue(monthlyOnce);
			CodeValues yearlyOnce=new CodeValues();
			yearlyOnce.setCodeName("YEARLY");
			yearlyOnce.setCodeDesc("Yearly Once");
			yearlyOnce.setCodeGroup(tempTransactionType);
			ServiceFactory.getCodeValuesService().addCodeValue(yearlyOnce);
			
			
			// Budget Status(B_ACTIVE , B_CLOSED , B_SUSPENDED , B_TERMINATED
			CodeGroup budgetStatus=new CodeGroup();
			budgetStatus.setGroupStatus(true);
			budgetStatus.setGroupCode("BUDGET_STATUS");
			budgetStatus.setGroupDesc("Budget Status");
			ServiceFactory.getCodeGroupService().addGroup(budgetStatus);
			CodeValues b_active=new CodeValues();
			b_active.setCodeGroup(budgetStatus);
			b_active.setCodeName("B_ACTIVE");
			b_active.setCodeDesc("Active Budget");
			ServiceFactory.getCodeValuesService().addCodeValue(b_active);
			
			CodeValues b_inactive=new CodeValues();
			b_inactive.setCodeGroup(budgetStatus);
			b_inactive.setCodeName("B_INACTIVE");
			b_inactive.setCodeDesc("In-Active Budget");
			ServiceFactory.getCodeValuesService().addCodeValue(b_inactive);
			
			CodeValues b_closed=new CodeValues();
			b_closed.setCodeGroup(budgetStatus);
			b_closed.setCodeName("B_CLOSED");
			b_closed.setCodeDesc("Closed Budget");
			ServiceFactory.getCodeValuesService().addCodeValue(b_closed);
			
			CodeValues b_suspended=new CodeValues();
			b_suspended.setCodeGroup(budgetStatus);
			b_suspended.setCodeName("B_SUSPENDED");
			b_suspended.setCodeDesc("Suspended Budget");
			ServiceFactory.getCodeValuesService().addCodeValue(b_suspended);
			
			CodeValues b_terminated=new CodeValues();
			b_terminated.setCodeGroup(budgetStatus);
			b_terminated.setCodeName("B_TERMINATED");
			b_terminated.setCodeDesc("Terminated Budget");
			ServiceFactory.getCodeValuesService().addCodeValue(b_terminated);
		}
		
		List<UserCodeValue> userCodeValueList=ServiceFactory.getUserCodeValueService().getRecords(user);
		if(userCodeValueList==null || userCodeValueList.size()==0)
		{
			List<CodeValues> codeValues=ServiceFactory.getCodeValuesService().getRecords();
			for(CodeValues codeValue:codeValues)
			{
				if(codeValue.getCodeName().compareTo(MyConstants.INCOME_CATEGORY)==0)
				{
					//CATEGORIES TYPE
					UserCodeValue salaryCategory=new UserCodeValue();
					salaryCategory.setUser(user);
					salaryCategory.setCodeValues(codeValue);
					salaryCategory.setUserCodeName("Salary");
					salaryCategory.setUserCodeDesc("Salary");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(salaryCategory);
					
					UserCodeValue bankDeposits=new UserCodeValue();
					bankDeposits.setUser(user);
					bankDeposits.setCodeValues(codeValue);
					bankDeposits.setUserCodeName("Bank Interest");
					bankDeposits.setUserCodeDesc("Bank Interest");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(bankDeposits);
					
					UserCodeValue propertyRent=new UserCodeValue();
					propertyRent.setUser(user);
					propertyRent.setCodeValues(codeValue);
					propertyRent.setUserCodeName("Property Rent");
					propertyRent.setUserCodeDesc("Property Rent");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(propertyRent);
					
					UserCodeValue sharesProperty=new UserCodeValue();
					sharesProperty.setUser(user);
					sharesProperty.setCodeValues(codeValue);
					sharesProperty.setUserCodeName("Market Shares");
					sharesProperty.setUserCodeDesc("Market Shares");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(sharesProperty);
					
					UserCodeValue bonus=new UserCodeValue();
					bonus.setUser(user);
					bonus.setCodeValues(codeValue);
					bonus.setUserCodeName("Bonus");
					bonus.setUserCodeDesc("Bonus");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(bonus);
					
					// PAYEES
					Payee employer=new Payee();
					employer.setUser(user);
					employer.setPayeeCateogry(codeValue);
					employer.setPayeeStatus(true);
					employer.setPayeeName("My Employer");
					employer.setPayeeDesc("My Employer");
					ServiceFactory.getPayeeService().addPayee(employer);
					
					Payee bankBranch=new Payee();
					bankBranch.setUser(user);
					bankBranch.setPayeeCateogry(codeValue);
					bankBranch.setPayeeStatus(true);
					bankBranch.setPayeeName("SBI Bank");
					bankBranch.setPayeeDesc("SBI Bank");
					ServiceFactory.getPayeeService().addPayee(bankBranch);
					
					Payee tenant=new Payee();
					tenant.setUser(user);
					tenant.setPayeeCateogry(codeValue);
					tenant.setPayeeStatus(true);
					tenant.setPayeeName("Property Tenants");
					tenant.setPayeeDesc("Property Tenants");
					ServiceFactory.getPayeeService().addPayee(tenant);
					
					Payee shareHolders=new Payee();
					shareHolders.setUser(user);
					shareHolders.setPayeeCateogry(codeValue);
					shareHolders.setPayeeStatus(true);
					shareHolders.setPayeeName("Share Holders");
					shareHolders.setPayeeDesc("Share Holders");
					ServiceFactory.getPayeeService().addPayee(shareHolders);
					
					
				}
				else if(codeValue.getCodeName().compareTo(MyConstants.EXPENSE_CATEGORY)==0)
				{
					UserCodeValue homeRentCategory=new UserCodeValue();
					homeRentCategory.setUser(user);
					homeRentCategory.setCodeValues(codeValue);
					homeRentCategory.setUserCodeName("House Rent");
					homeRentCategory.setUserCodeDesc("House Rent");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(homeRentCategory);
					
					UserCodeValue vehical=new UserCodeValue();
					vehical.setUser(user);
					vehical.setCodeValues(codeValue);
					vehical.setUserCodeName("Vehical");
					vehical.setUserCodeDesc("Vehical");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(vehical);
					
					UserCodeValue health=new UserCodeValue();
					health.setUser(user);
					health.setCodeValues(codeValue);
					health.setUserCodeName("Health");
					health.setUserCodeDesc("Health");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(health);
					
					UserCodeValue entertainment=new UserCodeValue();
					entertainment.setUser(user);
					entertainment.setCodeValues(codeValue);
					entertainment.setUserCodeName("Entertainment");
					entertainment.setUserCodeDesc("Entertainment");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(entertainment);
					
					UserCodeValue food=new UserCodeValue();
					food.setUser(user);
					food.setCodeValues(codeValue);
					food.setUserCodeName("Food");
					food.setUserCodeDesc("Food");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(food);
					
					UserCodeValue shopping=new UserCodeValue();
					shopping.setUser(user);
					shopping.setCodeValues(codeValue);
					shopping.setUserCodeName("Shopping");
					shopping.setUserCodeDesc("Shopping");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(shopping);
					
					UserCodeValue travell=new UserCodeValue();
					travell.setUser(user);
					travell.setCodeValues(codeValue);
					travell.setUserCodeName("Travel");
					travell.setUserCodeDesc("Travel");
					ServiceFactory.getUserCodeValueService().addUserCodeValue(travell);
					
					
					// PAYEES
					Payee houseOwner=new Payee();
					houseOwner.setUser(user);
					houseOwner.setPayeeCateogry(codeValue);
					houseOwner.setPayeeStatus(true);
					houseOwner.setPayeeName("House Owner");
					houseOwner.setPayeeDesc("House Owner");
					ServiceFactory.getPayeeService().addPayee(houseOwner);
					
					Payee gasStation=new Payee();
					gasStation.setUser(user);
					gasStation.setPayeeCateogry(codeValue);
					gasStation.setPayeeStatus(true);
					gasStation.setPayeeName("Gas Station");
					gasStation.setPayeeDesc("Gas Station");
					ServiceFactory.getPayeeService().addPayee(gasStation);
					
					Payee hospital=new Payee();
					hospital.setUser(user);
					hospital.setPayeeCateogry(codeValue);
					hospital.setPayeeStatus(true);
					hospital.setPayeeName("Hospital");
					hospital.setPayeeDesc("Hospital");
					ServiceFactory.getPayeeService().addPayee(hospital);
					
					Payee shoppingMall=new Payee();
					shoppingMall.setUser(user);
					shoppingMall.setPayeeCateogry(codeValue);
					shoppingMall.setPayeeStatus(true);
					shoppingMall.setPayeeName("Shopping Mall");
					shoppingMall.setPayeeDesc("Shopping Mall");
					ServiceFactory.getPayeeService().addPayee(shoppingMall);
				}
			}
		}
	}
	@Override
	public void addUser(User user) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.save(user);			
			session.getTransaction().commit();
			user=validateUser(user);
			addDefaultEntries(user);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
	}

	@Override
	public void delete(User user) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			session.delete(user);
			session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
	}

	@Override
	public List<User> getRecords() {
		List<User> userslist=new ArrayList<User>();
		int pageNumber=0;
		int pageSize=3;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Query query=session.createQuery("from User");
			userslist=query.list();
			session.getTransaction().commit();
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
		return userslist;
	}

	@Override
	public User getRecord(int personID) {
		User user = new User();
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			user=(User)session.get(User.class, personID);
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return user;
	}

	@Override
	public List<User> getActiveRecords(boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord(User user) {
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			User tempUser=(User)session.get(User.class, user.getPersonID());
			
			tempUser.setUserCode(user.getUserCode());
			tempUser.setUserStatus(user.isUserStatus());
			tempUser.setFirstName(user.getFirstName());
			tempUser.setLastName(user.getLastName());
			tempUser.setEmailID(user.getEmailID());
						
			session.getTransaction().commit();
			session.update(tempUser);
			
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
		}
		finally
		{
			session.close();
		}
		
	}
	@Override
	public boolean isUserNameAvailable(User user)
	{
		boolean isUserNameAlreadyAvailable=true;
		List<User> tempUser;
		Session session=null;
		try
		{
			session=HibernateUtil.getSession();
			
			Criteria criteria=session.createCriteria(User.class)
					.add(Restrictions.eq(UserColumns.USER_CODE.toString(),user.getUserCode()));
			tempUser=(List<User>)criteria.list();
			if(tempUser==null || tempUser.size()==0)
			{
				isUserNameAlreadyAvailable=false;
			}
			else
			{
				isUserNameAlreadyAvailable=true;
			}
			
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
			isUserNameAlreadyAvailable=true;
		}
		finally
		{
			session.close();
		}
		return isUserNameAlreadyAvailable;
	}
	@Override
	public User validateUser(User user) {
		User tempUser;
		Session session=null;
		try
		{
			if(user==null)
				return null;
			
			session=HibernateUtil.getSession();
			//session.beginTransaction();
			Criteria criteria=session.createCriteria(User.class)
					.add(Restrictions.eq(UserColumns.USER_CODE.toString(),user.getUserCode()))
					.add(Restrictions.eq(UserColumns.PASSWORD.toString(),user.getPassword()));
			tempUser=(User)criteria.uniqueResult();
			
		}
		catch(HibernateException hiberException)
		{
			hiberException.printStackTrace();
			return null;
		}
		finally
		{
			session.close();
		}
		return tempUser;
	}
}
