/*
 * Copyright (c) 2025 Techservice Engineering Team.
 * All rights reserved.
 *
 * This software is proprietary and confidential. It may not be reproduced,
 * distributed, or transmitted in any form or by any means, including photocopying,
 * recording, or other electronic or mechanical methods, without the prior written
 * permission of Techservice Engineering Team.
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 *
 * For any questions regarding this license, please contact:
 * Techservice Engineering Team
 * Email: engineering@techservice.com
 */ 
package com.techservices.digitalbanking.core.configuration.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

import static com.techservices.digitalbanking.core.util.AppUtil.ROLES;


@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long jwtExpirationMs = 86400000; // 24 hours
    public static final long MAX_AGE_SECONDS = 3600L;
    public static final long HSTS_MAX_AGE_SECONDS = 31536000L;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
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
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(String subject, long expirationMs, Map<String, Object> claims) {
        Map<String, Object> mutableClaims = claims != null ? new HashMap<>(claims) : new HashMap<>();

        return Jwts.builder()
                .setClaims(mutableClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
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
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (JwtException e) {
            return false;
        }
    }

    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
