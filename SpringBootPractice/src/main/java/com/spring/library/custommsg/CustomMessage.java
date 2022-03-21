package com.spring.library.custommsg;

public class CustomMessage {

	private String message;
	private String type;
	public CustomMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CustomMessage(String message, String type) {
		super();
		this.message = message;
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

