package com.subrutin.catalog.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	private String username;
	
	private RawAccessJwtToken rawAccessJwtToken;

	
	//sebelum otentikasi
	public JwtAuthenticationToken(RawAccessJwtToken rawAccessJwtToken) {
		super(null);
		this.rawAccessJwtToken = rawAccessJwtToken;
		super.setAuthenticated(false);
	}	
	
	//setelah otentikasi
	public JwtAuthenticationToken(String username, 
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.username = username;
		this.setAuthenticated(true);
	}

	


	@Override
	public Object getCredentials() {
		return this.rawAccessJwtToken;
	}

	@Override
	public Object getPrincipal() {
		return this.username;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.rawAccessJwtToken=null;
	}
	
	

}
