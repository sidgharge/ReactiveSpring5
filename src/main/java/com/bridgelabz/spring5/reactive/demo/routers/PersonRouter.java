package com.bridgelabz.spring5.reactive.demo.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.bridgelabz.spring5.reactive.demo.RequestHandler;

@Configuration
public class PersonRouter {

	@Autowired
	private RequestHandler handler;

	@Bean
	public RouterFunction<?> routes() {
		return RouterFunctions.nest(RequestPredicates.path("/persons"),
				RouterFunctions.route(RequestPredicates.GET("/"), handler::getPersons)
						.andRoute(RequestPredicates.POST("/"), request -> {
							return handler.savePerson(request);
						}));
	}
}
