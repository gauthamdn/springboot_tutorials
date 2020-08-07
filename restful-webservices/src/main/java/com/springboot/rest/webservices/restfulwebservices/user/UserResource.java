package com.springboot.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;


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
			
			//HATEOAS - Hyper media as the engine of application state - this is about not just returning data, but also telling about how to get data for all the users, other details etc
			//https://stackoverflow.com/questions/25352764/hateoas-methods-not-found
			// this will enable to add links to all users to our response
			//Resource<User> resource = new Resource<User>(user);
		
			
			//Resource<User> resource = new Resource<User>(user);
			// add link to this resource using class controllerLinkBuilder
			//ControllerLinkBuilder.linkTo(controller)
			//or define import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
			
			//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
//			In case you are using HATEOAS v1.0 and above (Spring boot >= 2.2.0), do note that the classnames have changed. Notably the below classes have been renamed:
//
//				ResourceSupport changed to RepresentationModel
//				Resource changed to EntityModel
//				Resources changed to CollectionModel
//				PagedResources changed to PagedModel
//				ResourceAssembler changed to RepresentationModelAssembler
			
			//resource.add(linkTo.withRel("All-Users"));
		
			// change the return type from user to resource
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
	
	
	// create user with validation
	@PostMapping(path="/users/create_with_validation")
public ResponseEntity<Object> createUser3(@Valid @RequestBody User user) {
		
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
	

	
	//HATEOAS - Hyper media as the engine of application state
}
