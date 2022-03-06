package com.kitchentracker.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.kitchentracker.models.Recipe;

@Repository
public interface RecipeRepository extends ReactiveMongoRepository<Recipe,String>, CustomRecipeRepository{

}
