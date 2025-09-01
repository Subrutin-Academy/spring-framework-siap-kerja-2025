package com.subrutin.catalog.security.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements TokenExtractor{

	private static final String HEADER_PREFIX = "Bearer ";
	
	@Override
	public String extract(String payload) {
		//Bearer [jwt]
		if(StringUtils.isBlank(payload)) {
			return "";
		}
		if(payload.length()< HEADER_PREFIX.length()) {
			throw new AuthenticationServiceException("invalid authorization header");
		}
		
		return payload.substring(HEADER_PREFIX.length(), payload.length());
	}

}
