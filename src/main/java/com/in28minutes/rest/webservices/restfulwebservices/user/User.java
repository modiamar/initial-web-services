package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//@ApiModel(description="All details about the user.")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	
	@Size(min=2, message="Names should be two characters")
	private String name;
	
	//Must be in the past
	private String birthDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public User(String name, String birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}
	public User() {

	}
	
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthDate=%s]", id, name, birthDate);
	}
	
	
}
