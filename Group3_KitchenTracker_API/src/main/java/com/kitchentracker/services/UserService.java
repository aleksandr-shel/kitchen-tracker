package com.kitchentracker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.kitchentracker.models.Item;
import com.kitchentracker.models.User;
import com.kitchentracker.repositories.UserRepository;
import com.mongodb.MongoWriteException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRep;
	
	public Flux<User> getAll(){
		return userRep.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<User> getById(String id){
		return userRep.findById(id);
	}
	
	public Mono<User> login(String username, String password){
		Mono<User> userMono = userRep.findByUsernameAndPassword(username, password);
		if (userMono != null) {
			return userMono;
		}else {
			return Mono.empty();
		}
	}
	
	//used in RecipeController
	public boolean UserExists(String id) {
		return userRep.existsById(id).block();
	}
	
	//check if username exists in database
	public Mono getUserWithUsername(String username) {
		return userRep.findByUserName(username);
	}
	
	
	public void addLikedRecipe(String userId, String recipeId) {
		Mono<User> monoUser = userRep.findById(userId);
		User user = monoUser.block();
		user.getLikedRecipes().add(recipeId);
		userRep.save(user).block();
	}
	
	public void removeLikedRecipe(String userId, String recipeId) {
		Mono<User> monoUser = userRep.findById(userId);
		User user = monoUser.block();
		user.getLikedRecipes().remove(recipeId);
		userRep.save(user).block();
	}
	
	
	
	public void addCreatedRecipe(String userId, String recipeId) {
		Mono<User> monoUser = userRep.findById(userId);
		User user = monoUser.block();
		user.getCreatedRecipes().add(recipeId);
		userRep.save(user).block();
	}
	
	public void removeCreatedRecipe(String userId, String recipeId) {
		Mono<User> monoUser = userRep.findById(userId);
		User user = monoUser.block();
		user.getCreatedRecipes().remove(recipeId);
		userRep.save(user).block();
	}
	
	public void addLikedAndCreatedRecipe(String userId, String recipeId) {
		Mono<User> monoUser = userRep.findById(userId);
		User user = monoUser.block();
		user.getCreatedRecipes().add(recipeId);
		user.getLikedRecipes().add(recipeId);
		userRep.save(user).block();
	}
	
	//need changes here? when user register name that already exists, it throws error
	// should we handle it so it returns message?
	public Mono createUser(User user) {
		return userRep.save(user);
	}
	
	public Mono deleteUser(String id) {
		return userRep.deleteById(id);
	}
	
	public Mono updateUser(String id, User userUpdated) {
		if (userRep.existsById(id).block()) {
			User user = userRep.findById(id).block();
			user.setFirstName(userUpdated.getFirstName());
			user.setLastName(userUpdated.getLastName());
			user.setPassword(userUpdated.getPassword());
			user.setUsername(userUpdated.getUsername());
			user.setEmail(userUpdated.getEmail());
			return userRep.save(user);
		} else {
			return Mono.empty();
		}
	}
	
	public ArrayList<Item> getInventory(String id){
		if (userRep.existsById(id).block()) {
			return userRep.findById(id).block().getInventory();
		} else {
			return new ArrayList<Item>();
		}
	}
	
	public Mono<Item> addItem(String id, Item item) {
		User user = userRep.findById(id).block();
		user.getInventory().add(item);
		userRep.save(user).block();
		return Mono.just(item);
	}
	
	public Mono<Item> getItem(String id, String itemId) {
		User user = userRep.findById(id).block();
		List<Item> inventory = user.getInventory();
		for (Item _item : inventory) {
			if (_item.getItemId().equals(itemId)) {
				return Mono.just(_item);
			}
		}
		return Mono.empty();
	}
	
	public Mono removeItem(String id, String itemId) {
		User user = userRep.findById(id).block();
		boolean isDeleted = user.getInventory().removeIf(x -> 
			x.getItemId().equals(itemId)
		);
		userRep.save(user).block();
		return Mono.just(isDeleted);
	}
	
	public Mono<Item> updateItem(String id, String itemId, Item item) {
		User user = userRep.findById(id).block();
		List<Item> inventory = user.getInventory();
		for (Item _item : inventory) {
			if (_item.getItemId().equals(itemId)) {
				item.setItemId(itemId);
				inventory.set(inventory.indexOf(_item), item);
				userRep.save(user).block();
				return Mono.just(item);
			}
		}
		userRep.save(user).block();
		return Mono.empty();
	}
}
