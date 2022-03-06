package com.kitchentracker.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.kitchentracker.models.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String>, CustomUserRepository {
	
}
