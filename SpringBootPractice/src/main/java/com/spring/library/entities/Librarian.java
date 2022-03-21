package com.spring.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Librarian {

	@Id
	@Column(unique = true)  
	@Size(min=5, max=50, message = "Min 5 and Max 50 characters are allowed")
	private String id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Size(min=5,max=50,  message = "Min 5 and Max 50 characters are allowed")
	private String password;

	public Librarian() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Librarian(String id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Librarian [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

	
}
