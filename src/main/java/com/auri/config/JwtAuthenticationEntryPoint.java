package com.auri.config;



import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;



@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
//		logger.error("Responding with unauthorized error. Message - {}", e.getMessage());
//		ApiResponse apiresponse = new ApiResponse("401", "access denied");
//		Gson g = new Gson();
//		String jsonString = g.toJson(apiresponse);
//		httpServletResponse.getWriter().println(jsonString);
		
//		httpServletResponse.setHeader("access denied", "invalid user role");
//		httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());		
		httpServletResponse.sendError(HttpStatus.FORBIDDEN.value(), "access denied");
	}
}
