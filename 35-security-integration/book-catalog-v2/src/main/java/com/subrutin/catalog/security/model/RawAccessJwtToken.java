package com.subrutin.catalog.security.model;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RawAccessJwtToken implements Token {

	private final String token;

	public Jws<Claims> parseClaims(SecretKey secret){
		return Jwts.parser()
				.verifyWith(secret)
				.build()
				.parseSignedClaims(token);
	}
	
	@Override
	public String getToken() {
		return this.token;
	}

}
