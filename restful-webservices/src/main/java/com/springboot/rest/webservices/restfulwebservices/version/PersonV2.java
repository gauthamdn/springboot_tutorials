package com.springboot.rest.webservices.restfulwebservices.version;

public class PersonV2 {

	public Name name;
	
	public PersonV2() {
		super();
	}
	

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	
	
}
