package com.bridgelabz.spring5.reactive.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.spring5.reactive.demo.PersonRepository;
import com.bridgelabz.spring5.reactive.demo.models.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Flux<Person> getPersons() {
		return personRepository.findAll();
	}

	public Mono<Person> getPerson(String id) {
		return personRepository.findById(id);
	}

	public Mono<Person> savePerson(Mono<Person> monoPerson) {
		return monoPerson.flatMap(person -> {
			return personRepository.save(person);
		});
	}
}
