package com.springboot.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	//retrieveAllUsers
	@GetMapping(path="/jpa/users")
	public List<User> retrieveAllUsers(){		
		return userRepository.findAll();
	}
	
	//retrieveUser(int id)
	@GetMapping(path="/jpa/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		return userRepository.findById(id);
	}
	
	//retrieveUser(int id) with exception handling
	
		@GetMapping(path="/jpa/users_retrieve/{id}")
		public Optional<User> retrieveUser1(@PathVariable int id) {
			Optional<User> user = userRepository.findById(id);
			
			if(!user.isPresent()) {
				throw new UserNotFoundException("id : "+id);
			}
			
			return user;
		}
	
	
	// now @RequestBody annotation maps the input the user object
	@PostMapping(path="/jpa/users/create")
	public void createUser(@RequestBody User user) {
		
		User savedUser = userRepository.save(user);

	}
	
	// create user with response 
	@PostMapping(path="/jpa/users")
	public ResponseEntity<Object> createUser2(@RequestBody User user) {
		
		User savedUser = userRepository.save(user);
		
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
	@PostMapping(path="/jpa/users/create_with_validation")
public ResponseEntity<Object> createUser3(@Valid @RequestBody User user) {
		
		User savedUser = userRepository.save(user);
		
		// now to send response . to send status created and the url of the user saved
		
		// get the current request uri using below ServletUriComponentsBuilder.fromCurrentRequest() method
		// and append the path 
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
				
	}
	
	
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		
	}
	

	
	//HATEOAS - Hyper media as the engine of application state
	
	
	
	//retrieveAllUsers
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrieveAllUsers(@PathVariable int id){		
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id : "+ id);
		}
		
		return userOptional.get().getPosts();
		
		 
	}
	
	
	// create posts
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity createPost(@PathVariable int id, @RequestBody Post post) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!(userOptional.isPresent())) {
			throw new UserNotFoundException("id : "+id);
		}
		
		User user = userOptional.get();
		post.setUser(user);
		
		postRepository.save(post);
		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(post.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
