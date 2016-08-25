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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="UserCodeValue")
public class UserCodeValue implements Comparator<UserCodeValue>
{
	@Id
	//@SequenceGenerator(name="seq_user_code",sequenceName="SEQ_USER_CODEVALUE",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_user_code")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userCodeID")
	private int userCodeID;
	private String userCodeName;
	private String userCodeDesc;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="userCodeValueID")
	private CodeValues codeValues;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	
	
	
	public CodeValues getCodeValues() {
		return codeValues;
	}
	public void setCodeValues(CodeValues codeValues) {
		this.codeValues = codeValues;
	}
	public int getUserCodeID() {
		return userCodeID;
	}
	public void setUserCodeID(int userCodeID) {
		this.userCodeID = userCodeID;
	}
	public String getUserCodeName() {
		return userCodeName;
	}
	public void setUserCodeName(String userCodeName) {
		this.userCodeName = userCodeName;
	}
	public String getUserCodeDesc() {
		return userCodeDesc;
	}
	public void setUserCodeDesc(String userCodeDesc) {
		this.userCodeDesc = userCodeDesc;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public static Comparator<UserCodeValue> UserCodeValueNameComparator=new Comparator<UserCodeValue>(){
		@Override
		public int compare(UserCodeValue objectOne, UserCodeValue objectTwo) {
			
			return objectOne.getUserCodeName().toUpperCase().compareTo(objectTwo.getUserCodeName().toUpperCase());
		}	
	};
	public static Comparator<UserCodeValue> UserCodeValueTypeDescComparator=new Comparator<UserCodeValue>(){
		@Override
		public int compare(UserCodeValue objectOne, UserCodeValue objectTwo) {
			
			return objectOne.getCodeValues().getCodeDesc().toUpperCase().compareTo(objectTwo.getCodeValues().getCodeDesc().toUpperCase());
		}	
	};

	@Override
	public int compare(UserCodeValue o1, UserCodeValue o2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
