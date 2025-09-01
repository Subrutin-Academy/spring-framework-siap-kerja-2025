package com.subrutin.catalog.security.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subrutin.catalog.dto.LoginRequestDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsernamePasswordAuthProcessingFilter extends AbstractAuthenticationProcessingFilter{

	private final ObjectMapper objectMapper;
	
	private final AuthenticationSuccessHandler successHandler;
	
	private final AuthenticationFailureHandler failuredHandler;
	
	public UsernamePasswordAuthProcessingFilter(String defaultFilterProcessesUrl, 
			ObjectMapper objectMapper, 
			AuthenticationFailureHandler failuredHandler, AuthenticationSuccessHandler successHandler) {
		super(defaultFilterProcessesUrl);
		this.objectMapper = objectMapper;
		this.successHandler = successHandler;
		this.failuredHandler = failuredHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info("===== Login");
		LoginRequestDTO dto = this.objectMapper.readValue(request.getReader(), LoginRequestDTO.class);
		if(StringUtils.isBlank(dto.username())||StringUtils.isBlank(dto.password())) {
			throw new BadRequestException("username.password.shoudbe.provided");
		}
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
		return this.getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		this.successHandler.onAuthenticationSuccess(request, response, authResult);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		this.failuredHandler.onAuthenticationFailure(request, response, failed);
	}
	
	

}
