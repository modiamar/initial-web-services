package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
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
	UserDaoService service;
	
	@Autowired
	UserRepo userSerivce;
	
	@PostMapping(path="/createUser")
	public User create(@RequestBody User user){
		userSerivce.save(user);
		return user;
	}
	
	//GET /Users
	//RetrieveAllUsers
	@GetMapping(path="/users")
	public List<User> getAllBoyz(){
		//List<User> findAll = service.findAll();
		List<User> findAll = userSerivce.findAll();
		return findAll;
	}
	
	// /Users/{id}
	//RetriveOneUser
	@GetMapping(path="/users/{id}")
	public Resource<User> getBoyz(@PathVariable int id){
		//User find = service.findOne(id);
		Optional<User> find = userSerivce.findById( id);
		System.out.println(find);
		if (!find.isPresent()) {
			throw new UserNotFoundException("id: " + id);
		}
		
		
		// HATEOAS concept will be used here
		//this guy gets the user from optional<user>
		Resource<User> resource = new Resource<User>(find.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllBoyz());
		//ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllBoyz());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	
	@DeleteMapping(path="/users/{id}")
	public void delete(@PathVariable int id){
		//User find = service.deleteById(id);
		UserRepo userSerivce2 = userSerivce;
		userSerivce2.deleteById( id);
	}
	
	//
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User save = service.save(user);
		//Returns current request URI
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		//appending {id}
		//replacing with save.getId()
		.path("{id}")
		.buildAndExpand(save.getId()).toUri();
		
		//Builds the new Uri
		return ResponseEntity.created(uri).build();
	}

}
