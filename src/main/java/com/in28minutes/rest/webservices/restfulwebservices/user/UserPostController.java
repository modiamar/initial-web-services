package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPostController {
	@Autowired
	UserRepo userService;
	
	@Autowired
	PostRepository postRepo;
	
	@PostMapping(value="user/{id}/posts/")
	public Post createPost(@PathVariable int id,@RequestBody Post post ){
		Optional<User> find = userService.findById( id);
		//System.out.println(find);
		if (!find.isPresent()) {
			throw new UserNotFoundException("id: " + id);
		}
		post.setUser(find.get());
		postRepo.save(post);
		return post;
	}
	
	@GetMapping(value="user/{id}/posts/")
	public List<Post> getPosts(@PathVariable int id){
		Optional<User> find = userService.findById( id);
		//System.out.println(find);
		if (!find.isPresent()) {
			throw new UserNotFoundException("id: " + id);
		}
		
		return find.get().getPosts();
	}
	

}
