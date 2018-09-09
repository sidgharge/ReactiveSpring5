package com.bridgelabz.spring5.reactive.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bridgelabz.spring5.reactive.demo.models.Person;

public interface PersonRepository extends ReactiveMongoRepository<Person, String>{
	
}
