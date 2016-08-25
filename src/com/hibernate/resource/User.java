package com.hibernate.resource;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="AppUser")
public class User extends Person
{
	
	private String userCode;
	private String password;
	private boolean userStatus;

	public User(){	}
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public boolean isUserStatus() {
		return userStatus;
	}
	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
