package com.hibernate.resource;

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

@Entity
@Table(name="RecurringTransaction")
public class RecurringTransaction 
{
	@Id
	//@SequenceGenerator(name="seq_recurringTrans",sequenceName="SEQ_RECU_TRANSACTION",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_recurringTrans")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recTransactionID")
	private int recTransactionID;
	private boolean isIncome;
	private boolean isExpense;
	private float amount;
	private String description;
	@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="categoryID")
	private UserCodeValue catergory;
	private String transactionDate;
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="payeeID")
	private Payee payee;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="personID",nullable=false)
	private User user;
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="recurringTransactionType")
	private CodeValues recurringTransactionType; // Daily , Weekly , Monthly , yearly
	private int noOfoccurenses;
	private String transsactionEndDate;
	
	
	public int getRecTransactionID() {
		return recTransactionID;
	}
	public void setRecTransactionID(int recTransactionID) {
		this.recTransactionID = recTransactionID;
	}
	public boolean isIncome() {
		return isIncome;
	}
	public void setIncome(boolean isIncome) {
		this.isIncome = isIncome;
	}
	public boolean isExpense() {
		return isExpense;
	}
	public void setExpense(boolean isExpense) {
		this.isExpense = isExpense;
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
	public Payee getPayee() {
		return payee;
	}
	public void setPayee(Payee payee) {
		this.payee = payee;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CodeValues getRecurringTransactionType() {
		return recurringTransactionType;
	}
	public void setRecurringTransactionType(CodeValues recurringTransactionType) {
		this.recurringTransactionType = recurringTransactionType;
	}
	public int getNoOfoccurenses() {
		return noOfoccurenses;
	}
	public void setNoOfoccurenses(int noOfoccurenses) {
		this.noOfoccurenses = noOfoccurenses;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTranssactionEndDate() {
		return transsactionEndDate;
	}
	public void setTranssactionEndDate(String transsactionEndDate) {
		this.transsactionEndDate = transsactionEndDate;
	}
}
