package com.kitchentracker.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kitchentracker.models.Item;
import com.kitchentracker.models.User;
import com.kitchentracker.services.UserService;
import com.mongodb.MongoWriteException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
//	@GetMapping("users")
//	public Flux<User> getUsers(){
//		return userService.getAll();
//	}
	
	//just an example, need changes
	@PostMapping("/register/user")
	public Mono register(@ModelAttribute User user) {
		return userService.createUser(user);
	}
	
	@PostMapping("/get/user/username")
	public Mono getUserWithUsername(@RequestParam("username") String username) {
		return userService.getUserWithUsername(username);
	}
	
	//just an example, need changes
	@PostMapping("/login/user")
	public Mono<User> login(@RequestParam("username") String username,@RequestParam("password") String password) {
		return userService.login(username, password);
	}
	
	@GetMapping("/user/{id}/inventory")
	public ArrayList<Item> getInventory(@PathVariable("id") String id){
		return userService.getInventory(id);
	}
	
	@PostMapping("/delete/user/{id}")
	public Mono deleteUser(@PathVariable("id") String id){
		return userService.deleteUser(id);
	}
	
	@PostMapping("/update/user/{id}")
	public Mono updateUser(@PathVariable("id") String id, @ModelAttribute User user) {
		return userService.updateUser(id, user);
	}
	
	
	@PostMapping("/user/{id}/inventory/add")
	public Mono addItem(@PathVariable("id") String id, @ModelAttribute Item item) {
		return userService.addItem(id, item);
	}
	
	
	@PostMapping("/user/{id}/inventory/delete/{itemId}")
	public Mono removeItem(@PathVariable("id") String id, @PathVariable("itemId") String itemId) {
		return userService.removeItem(id, itemId);
	}
	
	@GetMapping("/user/{id}/inventory/get/{itemId}")
	public Mono getItem(@PathVariable("id") String id, @PathVariable("itemId") String itemId) {
		return userService.getItem(id, itemId);
	}
	
	@PostMapping("/user/{id}/inventory/update/{itemId}")
	public Mono updateItem(@PathVariable("id") String id, @PathVariable("itemId") String itemId, @ModelAttribute Item item) {
		return userService.updateItem(id, itemId, item);
	}
	
	
	
	
}
