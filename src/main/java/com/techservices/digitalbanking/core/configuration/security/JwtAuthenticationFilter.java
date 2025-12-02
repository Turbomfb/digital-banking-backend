/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.techservices.digitalbanking.core.domain.data.model.AppUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	public JwtAuthenticationFilter(JwtUtil jwtUtil) {

		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		try {
			String token = authHeader.substring(7);
			if (jwtUtil.isTokenValid(token)) {
				AppUser appUser = jwtUtil.extractUserInfoFromJwt(token);

				Collection<GrantedAuthority> authorities = mapAuthorities(appUser);

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(appUser, null,
						authorities);

				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}

		filterChain.doFilter(request, response);
	}

	private Collection<GrantedAuthority> mapAuthorities(AppUser appUser) {

		if (appUser == null || appUser.getPermissions() == null) {
			return Collections.emptyList();
		}
		return appUser.getPermissions().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
	}
}
