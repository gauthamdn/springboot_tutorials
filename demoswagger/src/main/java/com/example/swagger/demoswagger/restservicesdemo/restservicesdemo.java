package com.example.swagger.demoswagger.restservicesdemo;

import java.util.Date;

public class restservicesdemo {

	private String message ;
	private Date timestamp;
	
	
	public restservicesdemo(String message) {
		this.message = message;
		this.timestamp = getTimestamp();
	}
	
	
	public restservicesdemo(String message, Date timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}
	
	
	public void setMessage(String message) {
		this.message = message;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public Date getTimestamp() {
		return timestamp;
	}


	@Override
	public String toString() {
		return "restservicesdemo [message=" + message + ", timestamp=" + timestamp + "]";
	}
	
	
	
	
}
