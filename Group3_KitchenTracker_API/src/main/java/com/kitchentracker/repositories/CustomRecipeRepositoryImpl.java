package com.kitchentracker.repositories;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kitchentracker.models.Recipe;

import reactor.core.publisher.Flux;

public class CustomRecipeRepositoryImpl implements CustomRecipeRepository {

	private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomRecipeRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	@Override
	public Flux<Recipe> getRecipesWithAuthorId(String id) {
		Query query = new Query(Criteria.where("author_id").is(id));
		
		return mongoTemplate.find(query, Recipe.class, "recipes");
	}

	@Override
	public Flux<Recipe> getListOfRecipesWithRecipeName(String searchKey) {
		Query query = new Query(Criteria.where("name").regex(Pattern.compile(searchKey, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		return mongoTemplate.find(query, Recipe.class, "recipes");
	}

	@Override
	public Flux<Recipe> getListOfRecipesWithIngredientName(String searchKey) {
		//advanced search or something 
		Query query = new Query(Criteria.where("ingredients").regex(Pattern.compile(searchKey, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		//Query query = new Query(Criteria.where("ingredients").regex(Pattern.compile(searchKey, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		return mongoTemplate.find(query, Recipe.class, "recipes");
	}


}
