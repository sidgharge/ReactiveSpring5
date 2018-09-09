package com.bridgelabz.spring5.reactive.demo.handlers;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bridgelabz.spring5.reactive.demo.models.MessageResponse;

@Component
@Order(-2)
public class CustomErrorHandler extends AbstractErrorWebExceptionHandler {

//	public CustomErrorHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
//			ErrorProperties errorProperties, ApplicationContext applicationContext) {
//		super(errorAttributes, resourceProperties, errorProperties, applicationContext);
//	}
	
	public CustomErrorHandler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ApplicationContext applicationContext, ServerCodecConfigurer serverCodecConfigurer) {
		super(errorAttributes, resourceProperties, applicationContext);
		super.setMessageWriters(serverCodecConfigurer.getWriters());
		super.setMessageReaders(serverCodecConfigurer.getReaders());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), request -> {
			MessageResponse resp = new MessageResponse(getError(request).getMessage(), -1);
			return ServerResponse.ok().body(BodyInserters.fromObject(resp));
		});
	}

	
}
