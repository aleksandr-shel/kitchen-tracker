package com.kitchentracker.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {
	
	
	@Id
	private String id;

	@Indexed(unique=true)
	private String username;
	
	private String password;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private ArrayList<Item> inventory;
	
	private ArrayList<String> likedRecipes;

	private ArrayList<String> createdRecipes;


	public User(String id, String username, String password, String email, String firstName, String lastName,
			ArrayList<Item> inventory, ArrayList<String> likedRecipes, ArrayList<String> createdRecipes) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.inventory = inventory;
		this.likedRecipes = likedRecipes;
		this.createdRecipes = createdRecipes;
	}

	

	public ArrayList<String> getCreatedRecipes() {
		if (createdRecipes == null) {
			createdRecipes =  new ArrayList<String>();
		}
		return createdRecipes;
	}



	public void setCreatedRecipes(ArrayList<String> createdRecipes) {
		this.createdRecipes = createdRecipes;
	}



	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public ArrayList<Item> getInventory() {
		if (inventory == null) {
			inventory =  new ArrayList<Item>();
		}
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public ArrayList<String> getLikedRecipes() {
		if (likedRecipes == null) {
			likedRecipes = new ArrayList<String>();
		}
		return likedRecipes;
	}

	public void setLikedRecipes(ArrayList<String> likedRecipes) {
		this.likedRecipes = likedRecipes;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
