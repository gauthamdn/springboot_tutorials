package com.springboot.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
	
	private String message;
	private String name ;
	
	public HelloWorldBean(String msg) {
		
		this.message = msg;
		this.name = "Spring boot";
		
	}
	
	

	public HelloWorldBean(String msg, String name) {
		this.message = msg;
		this.name = name;
		// TODO Auto-generated constructor stub
	}



	public String getMessage() {
		return message;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
	
	 

}
