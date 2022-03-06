package com.kitchentracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchentracker.models.Recipe;
import com.kitchentracker.repositories.RecipeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRep;
	
	public Mono createRecipe(Recipe recipe) {
		return recipeRep.save(recipe);
	}
	
	public Mono<Recipe> getRecipe(String id) {
		return recipeRep.findById(id);
	}
	
	public Mono updateRecipe(String id, Recipe recipe) {
		if (recipeRep.existsById(id).block()) {
			recipe.setId(id);
			return recipeRep.save(recipe);
		} else {
			return Mono.empty();
		}
	}
	
	
	public Flux<Recipe> getRecipesOfUser(String id){
		return recipeRep.getRecipesWithAuthorId(id);
	}
	
	public Mono deleteRecipe(String id) {
		return recipeRep.deleteById(id);
	}
	
	public Flux<Recipe> recipes(){
		return recipeRep.findAll();
	}
	
	
	public Flux<Recipe> searchRecipes(String searchStr){		
		return recipeRep.getListOfRecipesWithRecipeName(searchStr);
	}
	
	public Flux<Recipe> advancedSearchRecipes(String searchStr){
		return recipeRep.getListOfRecipesWithIngredientName(searchStr);
	}
}
