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
@Table(name="Expense")
public class Expense implements Comparator<Expense>
{
	@Id
	//@SequenceGenerator(name="seq_Expense",sequenceName="SEQ_EXPENSE",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_Expense")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="expenseID")
	private int expenseID=0;
	private float amount=(float) 0.0;
	private String description;
	@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="categoryID")
	private UserCodeValue category;
	private String transactionDate;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="personID",nullable=false)
	private User user;
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="payeeID")
	private Payee payee;
	
	public Payee getPayee() {
		return payee;
	}
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	public Expense()
	{
		
	}
	public int getExpenseID() {
		return expenseID;
	}

	public void setExpenseID(int expenseID) {
		this.expenseID = expenseID;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserCodeValue getCatergory() {
		return category;
	}

	public void setCatergory(UserCodeValue catergory) {
		this.category = catergory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public static Comparator<Expense> ExpenseDescComparator=new Comparator<Expense>(){
		@Override
		public int compare(Expense o1, Expense o2) {
			
			return o1.getDescription().compareTo(o2.getDescription());
		}	
	};
	
	public static Comparator<Expense> ExpenseAmountComparator=new Comparator<Expense>(){
		@Override
		public int compare(Expense o1, Expense o2) {
			if(o1.getAmount()<o2.getAmount())
			{
				return -1;
			}
			else if(o1.getAmount()>o2.getAmount()){
				return 1;
			}
			else
			{
				return 0;
			}
		}	
	};
	public static Comparator<Expense> ExpenseCategoryComparator=new Comparator<Expense>(){
		@Override
		public int compare(Expense expenseOne, Expense expenseTwo)
		{
			return expenseOne.getCatergory().getUserCodeDesc().compareTo(expenseTwo.getCatergory().getUserCodeDesc());
		}
	};
	
	public static Comparator<Expense> ExpensePayeeComparator=new Comparator<Expense>(){
		@Override
		public int compare(Expense expenseOne, Expense expenseTwo)
		{
			return expenseOne.getPayee().getPayeeDesc().compareTo(expenseTwo.getPayee().getPayeeDesc());
		}
	};
	@Override
	public int compare(Expense o1, Expense o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
}
