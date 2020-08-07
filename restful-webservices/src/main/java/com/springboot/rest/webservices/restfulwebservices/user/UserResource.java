package com.springboot.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	//retrieveAllUsers
	@GetMapping(path="/users")
	public List<User> retrieveAllUsers(){		
		return service.findAll();
	}
	
	//retrieveUser(int id)
	@GetMapping(path="/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return service.findOne(id);
	}
	
	//retrieveUser(int id) with exception handling
		@GetMapping(path="/users_retrieve/{id}")
		public User retrieveUser1(@PathVariable int id) {
			User user = service.findOne(id);
			
			if(user == null) {
				throw new UserNotFoundException("id : "+id);
			}
			
			return user;
		}
	
	
	// now @RequestBody annotation maps the input the user object
	@PostMapping(path="/users/create")
	public void createUser(@RequestBody User user) {
		
		User savedUser = service.save(user);

	}
	
	// create user with response 
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser2(@RequestBody User user) {
		
		User savedUser = service.save(user);
		
		// now to send response . to send status created and the url of the user saved
		
		// get the current request uri using below ServletUriComponentsBuilder.fromCurrentRequest() method
		// and append the path 
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
				
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		User user = service.deleteByID(id);
		if(user == null) {
			throw new UserNotFoundException("id - "+id);
		}
		
	return ResponseEntity.accepted().build();
	}
	
	
}
