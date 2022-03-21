package com.spring.library.entities;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message = "Bookcode must be unique.Please provide a new unique code")
	@Column(unique = true)
	private String bookCode;
	
	private String bookName;
	
	private String author;
	
//	@Generated(GenerationTime.ALWAYS)
//	@Temporal(javax.persistence.TemporalType.DATE)
	private LocalDate dataAdded = LocalDate.now();

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String bookCode, String bookName, String author) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
	}

	public Book(String bookCode, String bookName, String author, LocalDate dataAdded) {
		super();
		this.bookCode = bookCode;
		this.bookName = bookName;
		this.author = author;
		this.dataAdded = dataAdded;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
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

	public LocalDate getDataAdded() {
		return dataAdded;
	}

	public void setDataAdded(LocalDate dataAdded) {
		this.dataAdded = dataAdded;
	}	
	
}
