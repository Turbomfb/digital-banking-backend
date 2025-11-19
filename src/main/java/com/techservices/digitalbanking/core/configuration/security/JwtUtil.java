/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.security;

import java.util.*;
import java.util.function.Function;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.techservices.digitalbanking.common.domain.enums.UserType;
import com.techservices.digitalbanking.core.domain.data.model.AppUser;
import com.techservices.digitalbanking.customer.domian.data.model.Customer;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import static com.techservices.digitalbanking.core.util.AppUtil.ROLES;

@Component
@Slf4j
public class JwtUtil {

	private final SecretKey secretKey;
	private final long jwtExpirationMs = 86400000; // 24 hours
	public static final long MAX_AGE_SECONDS = 3600L;
	private final CustomerRepository customerRepository;
	public static final long HSTS_MAX_AGE_SECONDS = 31536000L;

	public JwtUtil(@Value("${jwt.secret}") String secret, CustomerRepository customerRepository) {

		this.customerRepository = customerRepository;
		if (secret == null || secret.trim().isEmpty()) {
			throw new IllegalStateException("JWT secret key cannot be null or empty");
		}
		if (secret.getBytes().length < 32) { // Ensure the key is at least 256 bits (32 bytes)
			throw new IllegalStateException("JWT secret key must be at least 256 bits (32 bytes).");
		}
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateToken(String username, List<String> roles) {

		return createToken(Map.of(ROLES, roles), username);
	}

	public String generateToken(String username, Map<String, Object> claims) {

		return createToken(claims, username);
	}

	public String generateToken(String username, List<String> roles, Map<String, Object> extraClaims) {

		Map<String, Object> claims = Map.of("roles", roles);
		claims.putAll(extraClaims);
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
	}

	public String generateToken(String subject, long expirationMs, Map<String, Object> claims) {

		Map<String, Object> mutableClaims = claims != null ? new HashMap<>(claims) : new HashMap<>();

		return Jwts.builder().setClaims(mutableClaims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirationMs))
				.signWith(secretKey, SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {

		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {

		return extractClaim(token, Claims::getExpiration);
	}

	@SuppressWarnings("unchecked")
	public List<String> extractRoles(String token) {

		Claims claims = extractAllClaims(token);
		return (List<String>) claims.get("roles");
	}

	@SuppressWarnings("unchecked")
	public List<String> extractPermissions(String token) {

		Claims claims = extractAllClaims(token);
		List<String> permissions = (List<String>) claims.get("permissions");
		return permissions != null ? permissions : Collections.emptyList();
	}

	public String extractClaim(String token, String claimName) {

		Claims claims = extractAllClaims(token);
		return claims.get(claimName, String.class);
	}

	public <T> T extractClaim(String token, String claimName, Class<T> tClass) {

		Claims claims = extractAllClaims(token);
		return claims.get(claimName, tClass);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {

		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
	}

	public boolean isTokenExpired(String token) {

		return extractExpiration(token).before(new Date());
	}

	public boolean isTokenValid(String token) {

		try {
			AppUser appUser = extractUserInfoFromJwt(token);
			Customer foundCustomer = customerRepository.findById(appUser.getUserId()).orElse(null);
			return foundCustomer != null && foundCustomer.isAuthenticated() && !isTokenExpired(token);
		} catch (Exception e) {
			log.error("Token validation error: {}", e.getMessage());
			return false;
		}
	}

	public boolean validateToken(String token, String username) {

		final String extractedUsername = extractUsername(token);
		return (extractedUsername.equals(username) && !isTokenExpired(token));
	}

	public AppUser extractUserInfoFromJwt(String token) {

		return new AppUser(this.extractClaim(token, "customerId", Long.class), this.extractClaim(token, "email"),
				this.extractClaim(token, "userType"), true, this.extractClaim(token, "isActive", Boolean.class),
				UserType.valueOf(this.extractClaim(token, "userType")), Collections.emptyList());
	}
}
