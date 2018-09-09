package com.bridgelabz.spring5.reactive.demo.services;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class MathsService {

	public static Flux<Integer> getTables(int num) {

//		if (num % 2 != 0) {
//			throw new RuntimeException("Number should be even");
//			// throw new Error("Number should be even");
//		}
//
//		return Flux.<Integer>create(sink -> {
//			for (int i = 1; i <= 10; i++) {
//				sink.next(num * i);
//			}
//			sink.complete();
//		});
		
		if(num % 2 != 0) {
			return Flux.error(new RuntimeException("Number is not even"));
		}
		return Flux.<Integer>just(num);
	}

	public static void main(String[] args) {
		getTables(6)
			.doOnError(e -> {
				System.out.println(e.getMessage());
			})
			.subscribe(n -> {
				System.out.print(n + " ");
			});
	}

	public Flux<Integer> even(int num) {
		if(num % 2 != 0) {
			return Flux.error(new RuntimeException("Number is not even"));
		}
		return Flux.<Integer>just(num);
	}
}
