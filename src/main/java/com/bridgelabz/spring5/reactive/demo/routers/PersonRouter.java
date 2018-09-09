package com.bridgelabz.spring5.reactive.demo.routers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.bridgelabz.spring5.reactive.demo.handlers.MathsHandler;
import com.bridgelabz.spring5.reactive.demo.handlers.RequestHandler;

@Configuration
public class PersonRouter {

	@Autowired
	private RequestHandler handler;

	@Autowired
	private MathsHandler mathsHandler;
	
	@Bean
	public RouterFunction<?> routes() {
		return RouterFunctions.nest(RequestPredicates.path("/persons"),
				RouterFunctions.route(RequestPredicates.GET("/"), handler::getPersons)
						.andRoute(RequestPredicates.POST("/"), handler::savePerson)
						.andRoute(RequestPredicates.GET("/{id}"), handler::getPerson)
		/* .andRoute(RequestPredicates.DELETE("/{id}"), handler::deletePerson) */);
	}

	@Bean
	public RouterFunction<?> mathsroutes() {
		return RouterFunctions.nest(RequestPredicates.path("/math"),
				RouterFunctions.route(RequestPredicates.GET("/even/{num}"), mathsHandler::even));
	}
}
