package com.hibernate.resource;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Person")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person 
{
	@Id
	//@SequenceGenerator(name="seq_parson",sequenceName="SEQ_PERSON",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_parson")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="personID")
	private int personID;
	
	private String firstName;
	private String lastName;
	private Date DOB;
	private String emailID;
	
	public Person()
	{
		
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
}
