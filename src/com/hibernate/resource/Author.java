package com.hibernate.resource;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Author")
public class Author
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int authorID;
	private String authorName;
	private String authorPwd;
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="person")
	private Person person;
	
	@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="authorStatus")
	private CodeValues authorStatus;
	
	public Author()
	{
		
	}
	public int getAuthorID() {
		return authorID;
	}
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorPwd() {
		return authorPwd;
	}
	public void setAuthorPwd(String authorPwd) {
		this.authorPwd = authorPwd;
	}
	public CodeValues getAuthorStatus() {
		return authorStatus;
	}
	public void setAuthorStatus(CodeValues authorStatus) {
		this.authorStatus = authorStatus;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
