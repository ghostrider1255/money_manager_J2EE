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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Chapter")
public class Chapter 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="chapterID")
	private int chapterID;
	private String chapterName;
	private boolean chapterStatus;
	@ManyToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="bookID",nullable=true)
	private Book book;
	
	
	public Chapter()
	{
		
	}
	public int getChapterID() {
		return chapterID;
	}
	public void setChapterID(int chapterID) {
		this.chapterID = chapterID;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public boolean isChapterStatus() {
		return chapterStatus;
	}
	public void setChapterStatus(boolean chapterStatus) {
		this.chapterStatus = chapterStatus;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
