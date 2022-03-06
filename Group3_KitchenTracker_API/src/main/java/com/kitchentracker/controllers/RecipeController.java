package com.kitchentracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kitchentracker.models.Recipe;
import com.kitchentracker.services.RecipeService;
import com.kitchentracker.services.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create/recipe")
	public Recipe createRecipe(@ModelAttribute Recipe recipe) {
		Mono<Recipe> monoRecipe = recipeService.createRecipe(recipe);
		Recipe recipeNew = monoRecipe.block();
		//don't need for now
//		if (recipeWithId.getAuthor_id() != null) {
//			if (userService.UserExists(recipeWithId.getAuthor_id())) {
//				userService.addLikedAndCreatedRecipe(recipeWithId.getAuthor_id(), recipeWithId.getId());
//			}
//		}
		return recipeNew;
	}
	
	
	// should add userId?
	@PostMapping("/update/recipe/{id}")
	public Mono updateRecipe(@PathVariable("id") String id, @ModelAttribute Recipe recipe) {
		return recipeService.updateRecipe(id, recipe);
	}
	
	
	//user can only delete recipe that he added
	//should we add here userId? 
	//or simply not show delete button for users who didn't create recipe on frontend
	@PostMapping("/delete/recipe/{id}")
	public Mono deleteRecipe(@PathVariable("id") String id) {
		return recipeService.deleteRecipe(id);
	}
	
	@GetMapping("/get/recipes")
	public Flux<Recipe> getRecipes(){
		return recipeService.recipes();
	}
	
	@GetMapping("/get/recipe/{id}")
	public Mono<Recipe> getRecipe(@PathVariable("id") String id){
		return recipeService.getRecipe(id);
	}
	
	@GetMapping("/get/recipes/{id}")
	public Flux<Recipe> getRecipesOfUser(@PathVariable("id") String id){
		return recipeService.getRecipesOfUser(id);
	}
	
	
	@PostMapping("/search/recipes")
	public Flux<Recipe> searchRecipes(@RequestParam("searchKey") String searchStr){
		if (searchStr.isEmpty()) {
			return recipeService.recipes();
		}
		return recipeService.searchRecipes(searchStr);
	}
	
	
	@PostMapping("/advanced-search/recipes")
	public Flux<Recipe> advancedSearchRecipes(@RequestParam("searchKey") String searchStr){
		if (searchStr.isEmpty()) {
			return recipeService.recipes();
		}
		return recipeService.advancedSearchRecipes(searchStr);
	}
	
	
}
