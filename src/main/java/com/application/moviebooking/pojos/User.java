package com.application.moviebooking.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	
	private String id;
	private String username;
	private String password;
	private boolean loggedIn;
	
	public User() {
		
	}
	
	public String getId() {
		return id;
	}
	
	@JsonIgnore
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof User) {
			User obj = (User) other;
			return this.username == obj.username;
		}
		return false;
	}
}
