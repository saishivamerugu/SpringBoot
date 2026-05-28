package com.project.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTService {

	@Value("${jwt.secret}")
	private String secretKeyBase64;

	@Value("${jwt.expiration}")
	private long jwtExpiration;

	// Extract username from token
	public String extractUsername(String token) {

		return extractClaim(token, Claims::getSubject);
	}

	// Generic method to extract any claim
	public <T> T extractClaim(
			String token,
			Function<Claims, T> claimsResolver) {

		final Claims claims =
				extractAllClaims(token);

		return claimsResolver.apply(claims);
	}

	// Generate token
	public String generateToken(String username) {

		return generateToken(
				new HashMap<>(),
				username);
	}

	// Generate token with extra claims
	public String generateToken(
			Map<String, Object> extraClaims,
			String username) {

		return buildToken(
				extraClaims,
				username,
				jwtExpiration);
	}

	// Build JWT token
	private String buildToken(
			Map<String, Object> extraClaims,
			String username,
			long expiration) {

		return Jwts.builder()

				.claims(extraClaims)

				.subject(username)

				.issuedAt(
						new Date(System.currentTimeMillis()))

				.expiration(
						new Date(System.currentTimeMillis()
								+ expiration))

				.signWith(getSignInKey())

				.compact();
	}

	// Validate token
	public boolean isTokenValid(
			String token,
			String username) {

		final String usernameFromToken =
				extractUsername(token);

		return (usernameFromToken.equals(username))
				&& !isTokenExpired(token);
	}

	// Check token expiration
	private boolean isTokenExpired(String token) {

		return extractExpiration(token)
				.before(new Date());
	}

	// Extract expiration date
	private Date extractExpiration(String token) {

		return extractClaim(
				token,
				Claims::getExpiration);
	}

	// Extract all claims
	private Claims extractAllClaims(String token) {

		return Jwts.parser()

				.verifyWith(getSignInKey())

				.build()

				.parseSignedClaims(token)

				.getPayload();
	}

	// Generate signing key
	private SecretKey getSignInKey() {

		byte[] keyBytes =
				Decoders.BASE64.decode(secretKeyBase64);

		return Keys.hmacShaKeyFor(keyBytes);
	}
}
