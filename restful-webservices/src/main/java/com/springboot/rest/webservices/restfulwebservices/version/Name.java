package com.springboot.rest.webservices.restfulwebservices.version;

public class Name {

	private String FirstName;
	private String LastName;
	
	public Name() {
		
	}
	
	
	public Name(String firstName, String lastName) {
		super();
		FirstName = firstName;
		LastName = lastName;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	
	
}
