package com.kitchentracker.repositories;

import com.kitchentracker.models.Recipe;

import reactor.core.publisher.Flux;

public interface CustomRecipeRepository {

	public Flux<Recipe> getRecipesWithAuthorId(String id);
	
	public Flux<Recipe> getListOfRecipesWithRecipeName(String searchKey);
	
	public Flux<Recipe> getListOfRecipesWithIngredientName(String searchKey);
}
