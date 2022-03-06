package com.kitchentracker.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {
	
	private String itemId;
	
	private String itemName;
	
	private Date dateExpiration;
	
	public Item(String itemName, Date dateExpiration) {
		super();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//		Date date = new Date();  
		ObjectId id = new ObjectId();
		this.itemId = id.toString();
		this.itemName = itemName;
		this.dateExpiration = dateExpiration;
	}
	
	

	public String getItemId() {
		return itemId;
	}



	public void setItemId(String itemId) {
		this.itemId = itemId;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getDateExpiration() { 
		if (dateExpiration == null) {
			return "";
		} else {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			return dateFormat.format(dateExpiration);
		}
	}


	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
//		try {
//			this.dateExpiration = new SimpleDateFormat("yyyy-MM-dd").parse(dateExpiration.toString());
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	
	
	
}
