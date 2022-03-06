package com.kitchentracker.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.kitchentracker.models.Contact;

@Repository
public interface ContactRepository extends ReactiveMongoRepository<Contact,String>{
}
