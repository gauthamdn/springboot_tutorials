package com.springboot.rest.webservices.restfulwebservices.version;

public class PersonV1 {
	
	private String Name;

	public PersonV1() {
		super();
	}

	
	public PersonV1(String name) {
		super();
		Name = name;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	

}
