package com.kitchentracker.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kitchentracker.models.User;

import reactor.core.publisher.Mono;

public class CustomUserRepositoryImpl implements CustomUserRepository{

	private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomUserRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
	@Override
	public Mono<User> findByUserName(String username) {
		Query query = new Query(Criteria.where("username").is(username));
		
		return mongoTemplate.findOne(query, User.class, "users");
	}

	@Override
	public Mono<User> findByUsernameAndPassword(String username, String password) {
		Query query = new Query(Criteria.where("username").is(username))
				.addCriteria(Criteria.where("password").is(password));
		return mongoTemplate.findOne(query, User.class, "users");
		
	}


}
