package com.kitchentracker.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("recipes")
public class Recipe {

	@Id
	private String id;
	
	private ArrayList<String> ingredients;
	
	private String name;
	
	private String type;
	
	private String description;

	private String imageUrl;
	
	private String author_id;

	public Recipe(String id, ArrayList<String> ingredients, String name, String type, String description,
			String imageUrl, String author_id) {
		super();
		this.id = id;
		this.ingredients = ingredients;
		this.name = name;
		this.type = type;
		this.description = description;
		this.imageUrl = imageUrl;
		this.author_id = author_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public ArrayList<String> getIngredients() {
		return ingredients;
	}



	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(String author_id) {
		this.author_id = author_id;
	}
	
	
}
