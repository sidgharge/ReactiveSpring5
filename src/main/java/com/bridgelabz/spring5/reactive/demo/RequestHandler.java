package com.bridgelabz.spring5.reactive.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bridgelabz.spring5.reactive.demo.models.Person;
import com.bridgelabz.spring5.reactive.demo.services.PersonService;

import reactor.core.publisher.Mono;

@Component
public class RequestHandler {
	
	@Autowired
	private PersonService demoService;
	
	public Mono<ServerResponse> getPersons(ServerRequest request){
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(demoService.getPersons(), Person.class);
	}

	public Mono<ServerResponse> savePerson(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(demoService.savePerson(request.bodyToMono(Person.class)), Person.class);
	}
}
