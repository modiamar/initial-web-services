package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;

	static {
		users.add(new User("Adam", "new Date()"));
		users.add(new User("Eve", "new Date()"));
		users.add(new User("Jack", "new Date()"));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne(int id){
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User save(User user){
		if (user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User deleteById(int id){
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()){
			if (iterator.next().getId() == id) {
				iterator.remove();
				return iterator.next();
			}
		}
		return null;
	}
}
