package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 getPersonV1(){
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(value="/person/param", params="version=2")
	public PersonV2 getPersonV2(){
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
