package com.kitchentracker.models;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("contacts")
public class Contact {

	@Id
	private String id;
	
	private String firstName;

	private String lName;
	
	private String email;
	
	private String subject;

	
	public Contact(String id, String firstName, String lName, String email, String subject) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lName = lName;
		this.email = email;
		this.subject = subject;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}

}
