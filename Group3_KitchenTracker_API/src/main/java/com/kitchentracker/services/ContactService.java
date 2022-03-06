package com.kitchentracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchentracker.models.Contact;
import com.kitchentracker.repositories.ContactRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRep;
	
	public Flux<Contact> getAll(){
		return contactRep.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono postContact(Contact contact) {
		return contactRep.save(contact);
	}
	
	public Mono getContact(String id) {
		return contactRep.findById(id);
	}
	
	
	public Flux<Contact> contacts(){
		return contactRep.findAll();
	}
}