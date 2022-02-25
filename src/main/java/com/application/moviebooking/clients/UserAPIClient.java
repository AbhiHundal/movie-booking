package com.application.moviebooking.clients;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.application.moviebooking.pojos.User;

import reactor.core.publisher.Mono;

@Component
public class UserAPIClient {
	
	private WebClient webClient;
	
	@Value("${user.apiclient.baseurl}")
	private String baseURL;
	
	public UserAPIClient() {
		
	}
	
	@PostConstruct
	public void setBaseUrl() {
		this.webClient = WebClient.create(this.baseURL);
	}
	
	public ResponseEntity<String> createNewUser(User user) {
		Mono<ResponseEntity<String>> mono = this.webClient
							.post()
							.uri("/register")
							.bodyValue(user)
							.exchangeToMono(response ->{
								return response.toEntity(String.class);
							});
		return mono.block();
	}
	
	public ResponseEntity<String> userLogin(Map<String,String> logindata,Cookie cookie){
		Mono<ResponseEntity<String>> mono = this.webClient
				.post()
				.uri("/login")
				.cookie(cookie.getName(), cookie.getValue())
				.bodyValue(logindata)
				.exchangeToMono(response ->{
					return response.toEntity(String.class);
				});
		return mono.block();
	}
	
	public ResponseEntity<String> userLogout(Cookie cookie){
		Mono<ResponseEntity<String>> mono = this.webClient
				.post()
				.uri("/logout")
				.cookie(cookie.getName(), cookie.getValue())
				.exchangeToMono(response ->{
					return response.toEntity(String.class);
				});
		return mono.block();
	}

	public ResponseEntity<Boolean> verifyUser(Cookie cookie) {
		// TODO Auto-generated method stub
		Mono<ResponseEntity<Boolean>> mono = this.webClient
				.get()
				.uri("/verify")
				.cookie(cookie.getName(), cookie.getValue())
				.exchangeToMono(response ->{
					return response.toEntity(Boolean.class);
				});
		return mono.block();
	}

	public ResponseEntity<String> userLogin(Map<String, String> logindata) {
		// TODO Auto-generated method stub
		Mono<ResponseEntity<String>> mono = this.webClient
				.post()
				.uri("/login")
				.bodyValue(logindata)
				.exchangeToMono(response ->{
					return response.toEntity(String.class);
				});
		return mono.block();

	}

	public ResponseEntity<String> userLogout() {
		// TODO Auto-generated method stub
		Mono<ResponseEntity<String>> mono = this.webClient
				.post()
				.uri("/logout")
				.exchangeToMono(response ->{
					return response.toEntity(String.class);
				});
		return mono.block();
	}

	public ResponseEntity<Boolean> verifyUser() {
		// TODO Auto-generated method stub
		Mono<ResponseEntity<Boolean>> mono = this.webClient
				.get()
				.uri("/verify")
				.exchangeToMono(response ->{
					return response.toEntity(Boolean.class);
				});
		return mono.block();
	}
}
