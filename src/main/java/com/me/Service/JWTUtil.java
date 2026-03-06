package com.me.Service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service

public class JWTUtil
{
	
	private static final String SECRET_KEY = "prachipatilavajkcadjsnjksadnkjewjwqknf keqnrklenkqrnknkqenkqd kcsnakl cdsnnlksanf"; //32 Char

	private SecretKey getSigningKey() {
		// ✅ Creates proper HMAC key
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).signWith(getSigningKey()) // ✅ now
																												// correct																							// type
				.compact();
	}
	
	
	

	public String extractUsername(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload().getSubject();
	}
	 public boolean isTokenValid(String token, String username) {
	        final String extractedUsername = extractUsername(token);
	        return extractedUsername.equals(username) && !isTokenExpired(token);
	    }

	 
	    private boolean isTokenExpired(String token) {
	        return Jwts.parser()
	                .verifyWith(getSigningKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload()
	                .getExpiration()
	                .before(new Date());
	    }

}
