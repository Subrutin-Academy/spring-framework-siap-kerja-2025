package com.subrutin.catalog.security.handler;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subrutin.catalog.security.util.JwtTokenFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsernamePasswordAuthSuccessHandler implements AuthenticationSuccessHandler {
	
	private final ObjectMapper objectMapper;
	
	private final JwtTokenFactory jwtTokenFactory;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String username = (String) authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
		String token =  jwtTokenFactory.createAccessJwtToken(username, authorities);
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("result", token);
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		objectMapper.writeValue(response.getWriter(), resultMap);
		
	}

}
