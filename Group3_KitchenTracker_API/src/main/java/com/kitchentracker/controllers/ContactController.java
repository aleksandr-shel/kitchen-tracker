package com.kitchentracker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchentracker.models.Contact;
import com.kitchentracker.services.ContactService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ContactController {

	@Autowired
	private ContactService contactService;
	
	// get all contacts
	@GetMapping("contacts")
	public Flux<Contact> getContacts(){
		return contactService.getAll();
	}
	
	// post contact 
	@PostMapping("/contact/post")
	public Mono save(@ModelAttribute Contact contact) {
		return contactService.postContact(contact);
	}
	
	// get contact by id
	@GetMapping("/contact/{id}")
	public Mono getItem(@PathVariable("id") String id, @PathVariable("id") String itemId) {
		return contactService.getContact(id);
	}
}
