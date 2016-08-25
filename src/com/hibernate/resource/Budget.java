package com.hibernate.resource;

import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Budget")
public class Budget 
{
	@Id
	//@SequenceGenerator(name="seq_Budget",sequenceName="SEQ_BUDGET",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_Budget")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="budgetID")
	private int budgetID;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="userID",nullable=false)
	private User user;
	private String defaultStartDate;
	private String defaultEndDate;
	private String budgetStartDate;
	private String budgetEndDate;
	private double budgetAmount;
	private double incomeAmount;
	private double expenseAmount;
	@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="budgetStatusID")
	private CodeValues budgetStatus;
	
	public int getBudgetID() {
		return budgetID;
	}
	public void setBudgetID(int budgetID) {
		this.budgetID = budgetID;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	public CodeValues getBudgetStatus() {
		return budgetStatus;
	}
	public void setBudgetStatus(CodeValues budgetStatus) {
		this.budgetStatus = budgetStatus;
	}
	public double getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	public double getExpenseAmount() {
		return expenseAmount;
	}
	public void setExpenseAmount(double expenseAmount) {
		this.expenseAmount = expenseAmount;
	}
	public String getDefaultStartDate() {
		return defaultStartDate;
	}
	public void setDefaultStartDate(String defaultStartDate) {
		this.defaultStartDate = defaultStartDate;
	}
	public String getDefaultEndDate() {
		return defaultEndDate;
	}
	public void setDefaultEndDate(String defaultEndDate) {
		this.defaultEndDate = defaultEndDate;
	}
	public String getBudgetStartDate() {
		return budgetStartDate;
	}
	public void setBudgetStartDate(String budgetStartDate) {
		this.budgetStartDate = budgetStartDate;
	}
	public String getBudgetEndDate() {
		return budgetEndDate;
	}
	public void setBudgetEndDate(String budgetEndDate) {
		this.budgetEndDate = budgetEndDate;
	}
	
	public static Comparator<Budget> BudgetIncomeAmountComparator=new Comparator<Budget>(){
		@Override
		public int compare(Budget budgetOne,Budget budgetTwo)
		{
			if(budgetOne.getIncomeAmount()<budgetTwo.getIncomeAmount())
			{
				return -1;
			}
			else if(budgetOne.getIncomeAmount()>budgetTwo.getIncomeAmount())
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	};
	public static Comparator<Budget> BudgetExpenseAmountComparator=new Comparator<Budget>(){
		@Override
		public int compare(Budget budgetOne,Budget budgetTwo)
		{
			if(budgetOne.getExpenseAmount()<budgetTwo.getExpenseAmount())
			{
				return -1;
			}
			else if(budgetOne.getExpenseAmount()>budgetTwo.getExpenseAmount())
			{
				return 1;
			}
			else
			{
				return 0;
			}
		}
	};
	public static Comparator<Budget> BudgetDateComparator=new Comparator<Budget>(){
		@Override
		public int compare(Budget budgetOne,Budget budgetTwo)
		{
			return budgetOne.getBudgetStartDate().compareTo(budgetTwo.getBudgetStartDate());
		}
	};
}
