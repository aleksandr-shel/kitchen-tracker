package com.kitchentracker.repositories;

import com.kitchentracker.models.User;

import reactor.core.publisher.Mono;

public interface CustomUserRepository {


	public Mono<User> findByUserName(String username);
	
	public Mono<User> findByUsernameAndPassword(String username, String password);
	
}
