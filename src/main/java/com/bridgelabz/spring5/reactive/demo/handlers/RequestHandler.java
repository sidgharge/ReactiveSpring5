package com.bridgelabz.spring5.reactive.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bridgelabz.spring5.reactive.demo.models.Person;
import com.bridgelabz.spring5.reactive.demo.services.PersonService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RequestHandler {
	
	@Autowired
	private PersonService demoService;
	
	public Mono<ServerResponse> getPersons(ServerRequest request){
		Flux<Person> personFlux = demoService.getPersons();
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(personFlux, Person.class);
	}
	
	public Mono<ServerResponse> getPerson(ServerRequest request){
		Mono<Person> personMono = demoService.getPerson(request.pathVariable("id"));
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(personMono, Person.class);
	}

	public Mono<ServerResponse> savePerson(ServerRequest request) {
		Mono<Person> personMono = demoService.savePerson(request.bodyToMono(Person.class));
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(personMono, Person.class);
	}
	
	public Mono<ServerResponse> deletePerson(ServerRequest request){
		Mono<Void> mono = demoService.deletePerson(request.pathVariable("id"));
		
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(mono , Void.class);
	}
}
