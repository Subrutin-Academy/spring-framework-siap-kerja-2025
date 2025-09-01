package com.subrutin.catalog.security.provider;

import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.subrutin.catalog.security.model.JwtAuthenticationToken;
import com.subrutin.catalog.security.model.RawAccessJwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
	
	private final SecretKey key;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		RawAccessJwtToken rawToken = (RawAccessJwtToken) authentication.getCredentials();
		if(StringUtils.isBlank(rawToken.getToken())) {
			return new JwtAuthenticationToken(null, 
					List.of(new SimpleGrantedAuthority("ROLE_GUEST")));
		}
		
		Jws<Claims> jwsClaims = rawToken.parseClaims(key);
		String subject = jwsClaims.getPayload().getSubject();
		List<String> scopes = jwsClaims.getPayload().get("scopes", List.class);
		return new JwtAuthenticationToken(subject, 
				scopes.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
