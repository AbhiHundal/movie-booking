package com.application.moviebooking.controllers;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.application.moviebooking.clients.UserAPIClient;
import com.application.moviebooking.pojos.User;


@Controller
public class UserController {
	
	@Autowired
	private UserAPIClient userApiClient;
	
	@PostMapping("/register")
	public ResponseEntity<String> createNewUser(@RequestBody User user){
		return userApiClient.createNewUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody Map<String,String> logindata,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies == null) return userApiClient.userLogin(logindata);
		return userApiClient.userLogin(logindata,cookies[0]);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> userLogout(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies == null) return userApiClient.userLogout();
		return userApiClient.userLogout(request.getCookies()[0]);
	}
	
	@GetMapping("/verify")
	public ResponseEntity<Boolean> verifyUser(HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies == null) return userApiClient.verifyUser();
		return userApiClient.verifyUser(request.getCookies()[0]);
	}
}
