package com.springboot.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController	
public class HelloWorldController {
	
	//GET
	//URI - /helloworld
	//method "Hello World"

	
	// way to create rest api 
	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")	
	public String helloWorld() {
		
		return "Hello World";
	}
	
	// creating GET method
	@GetMapping(path="/hello-world2")
	public String helloWorld2() {
		return "Hello Gautham";
	}
	
	
	// returning a Bean instead of string
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
		
	}
	
	//passing a variable in the get url
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		
		return new HelloWorldBean(String.format("Hello World, %s", name));
		//return new HelloWorldBean("Hello World", name);
	}
}
