package com.hibernate.resource;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Payee")
public class Payee 
{
	@Id
	//@SequenceGenerator(name="seq_payee",sequenceName="SEQ_PAYEE",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_payee")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="payeeID")
	private int payeeID;
	private String payeeName;
	private String payeeDesc;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="payeeCateogryID",nullable=false)
	private CodeValues payeeCateogry;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="userID",nullable=false)
	private User user;
	private boolean payeeStatus;
	
	
	public int getPayeeID() {
		return payeeID;
	}
	public void setPayeeID(int payeeID) {
		this.payeeID = payeeID;
	}
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getPayeeDesc() {
		return payeeDesc;
	}
	public void setPayeeDesc(String payeeDesc) {
		this.payeeDesc = payeeDesc;
	}
	public boolean isPayeeStatus() {
		return payeeStatus;
	}
	public void setPayeeStatus(boolean payeeStatus) {
		this.payeeStatus = payeeStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CodeValues getPayeeCateogry() {
		return payeeCateogry;
	}
	public void setPayeeCateogry(CodeValues payeeCateogry) {
		this.payeeCateogry = payeeCateogry;
	}
}
