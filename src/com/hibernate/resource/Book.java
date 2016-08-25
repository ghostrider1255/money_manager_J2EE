package com.hibernate.resource;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;



@Entity
@Table(name="Book")
public class Book 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookID")
	private int bookID;
	private String bookName;
	private String author;
	private boolean status;
	@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="categoryCodeValue")
	private CodeValues categoryCodeValue;
	@OneToMany(cascade={CascadeType.ALL})
	@IndexColumn(name="idx")
	private List<Chapter> chaptersList;
	
	public Book()
	{
		
	}
	public List<Chapter> getChaptersList() {
		return chaptersList;
	}
	public void setChaptersList(List<Chapter> chaptersList) {
		this.chaptersList = chaptersList;
	}
	public int getBookID() {
		return bookID;
	}
	public void setBookID(int bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public CodeValues getCategoryCodeValue() {
		return categoryCodeValue;
	}
	public void setCategoryCodeValue(CodeValues categoryCodeValue) {
		this.categoryCodeValue = categoryCodeValue;
	}
}
