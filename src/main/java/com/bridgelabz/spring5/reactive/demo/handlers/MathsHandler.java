package com.bridgelabz.spring5.reactive.demo.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bridgelabz.spring5.reactive.demo.models.MessageResponse;
import com.bridgelabz.spring5.reactive.demo.services.MathsService;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MathsHandler {
	
	@Autowired
	private MathsService mathsService;
	
	private EmitterProcessor<Integer> emitter = EmitterProcessor.<Integer>create();
	
	Flux<Integer> f = emitter.map(num -> {
		return num;
	});

	public Mono<ServerResponse> even(ServerRequest request) {
		int num = Integer.parseInt(request.pathVariable("num"));
		Flux<Integer> f = mathsService.even(num);
		return ServerResponse
				.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(f, Integer.class)
				.onErrorResume(e -> {
					MessageResponse response = new MessageResponse(e.getMessage(), -1);
					return  ServerResponse
							.ok()
							.contentType(MediaType.TEXT_EVENT_STREAM)
							.body(BodyInserters.fromObject(response));
				});
	}
	
	public Mono<ServerResponse> addToList(ServerRequest request) {
		emit(Integer.parseInt(request.pathVariable("num")));
		return ServerResponse.ok().build();
	}
	
	public Mono<ServerResponse> getList(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(f, Integer.class);
	}
	
	private void emit(int num) {
//		if(emitter.hasCompleted()) {
//			emitter = EmitterProcessor.<Integer>create();
//		}
		emitter.onNext(num);
//		if(num > 100) {
//			emitter.onComplete();
//		}
	}
}
