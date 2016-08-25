package com.hibernate.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Testing")
public class TestingBean 
{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int BeanID;
	@Column(name="Name")
	private String name;

	public int getBeanID() {
		return BeanID;
	}

	public void setBeanID(int beanID) {
		BeanID = beanID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
