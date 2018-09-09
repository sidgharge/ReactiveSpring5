package com.bridgelabz.spring5.reactive.demo.handlers;

import java.util.Map;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

	//No need of overriding
	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
		Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
		map.put("status", HttpStatus.BAD_REQUEST);
		map.put("message", "username is required");
		return map;
	}
}
