package com.spring.library.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {

	@Id
	private String authorName;

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(String authorName) {
		super();
		this.authorName = authorName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
	
}
