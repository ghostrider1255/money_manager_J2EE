package com.hibernate.resource;

import java.util.Comparator;
import java.util.Date;

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

import com.hibernate.utils.MyUtils;

@Entity
@Table(name="Income")
public class Income implements Comparator<Income>
{
	@Id
	//@SequenceGenerator(name="seq_Income",sequenceName="SEQ_INCOME",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_Income")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="incomeID")
	private int incomeID=0;
	private float amount=(float) 0.0;
	private String description;
	@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="categoryID")
	private UserCodeValue catergory;
	private String transactionDate;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="personID",nullable=false)
	private User user;
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="payeeID")
	private Payee payee;
	
	public int getIncomeID() {
		return incomeID;
	}

	public void setIncomeID(int incomeID) {
		this.incomeID = incomeID;
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
		return catergory;
	}

	public void setCatergory(UserCodeValue catergory) {
		this.catergory = catergory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Payee getPayee() {
		return payee;
	}

	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	
	public static Comparator<Income> IncomeDateComparator=new Comparator<Income>(){
		@Override
		public int compare(Income incomeOne, Income incomeTwo) {
			String transDateOne=incomeOne.getTransactionDate();
			String transDateTwo=incomeTwo.getTransactionDate();
			
			transDateOne=MyUtils.convertDateFormat(transDateOne, "dd-MM-yyyy", "yyyyMMdd");
			transDateTwo=MyUtils.convertDateFormat(transDateTwo, "dd-MM-yyyy", "yyyyMMdd");
			
			return transDateOne.compareTo(transDateTwo);
		}	
	};
	
	public static Comparator<Income> IncomeDescComparator=new Comparator<Income>(){
		@Override
		public int compare(Income incomeOne, Income incomeTwo) {
			
			return incomeOne.getDescription().compareTo(incomeTwo.getDescription());
		}	
	};
	
	public static Comparator<Income> IncomeAmountComparator=new Comparator<Income>(){
		@Override
		public int compare(Income incomeOne, Income incomeTwo) {
			if(incomeOne.getAmount()<incomeTwo.getAmount())
			{
				return -1;
			}
			else if(incomeOne.getAmount()>incomeTwo.getAmount()){
				return 1;
			}
			else
			{
				return 0;
			}
		}	
	};
	
	public static Comparator<Income> IncomeCategoryComparator=new Comparator<Income>(){
		@Override
		public int compare(Income incomeOne,Income incomeTwo)
		{
			return incomeOne.getCatergory().getUserCodeDesc().compareTo(incomeTwo.getCatergory().getUserCodeDesc());
		}
	};
	
	public static Comparator<Income> IncomePayeeComparator=new Comparator<Income>(){
		@Override
		public int compare(Income incomeOne,Income incomeTwo)
		{
			return incomeOne.getPayee().getPayeeDesc().compareTo(incomeTwo.getPayee().getPayeeDesc());
		}
	};
	@Override
	public int compare(Income o1, Income o2) {
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
