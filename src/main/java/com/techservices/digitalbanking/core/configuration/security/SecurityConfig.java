/* (C)2025 */
package com.techservices.digitalbanking.core.configuration.security;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.techservices.digitalbanking.core.configuration.SystemProperty;
import com.techservices.digitalbanking.customer.domian.data.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import static com.techservices.digitalbanking.core.configuration.security.JwtUtil.MAX_AGE_SECONDS;
import static com.techservices.digitalbanking.core.util.AppUtil.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final SystemProperty systemProperty;
	private final CustomerRepository customerRepository;

	@Value("${jwt.secret}")
	private String secretKey;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter)
			throws Exception {

		return http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth.requestMatchers(PUBLIC_ENDPOINTS).permitAll()
						.requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
						.requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
						.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();

		// Configure allowed origins
		configuration
				.setAllowedOriginPatterns(Stream.concat(Stream.of("http://localhost:*", systemProperty.getClientUrl()),
						systemProperty.getCorsAllowedOrigins().stream()).collect(Collectors.toList()));

		// Configure allowed methods
		configuration.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
				HttpMethod.PATCH.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));

		// Configure allowed headers
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin",
				"Access-Control-Request-Method", "X-User-Location", "User-Agent", "Access-Control-Request-Headers"));

		// Configure exposed headers
		configuration.setExposedHeaders(
				List.of("Access-Control-Allow-Origin", "Access-Control-Allow-Methods", "Access-Control-Allow-Headers"));

		configuration.setMaxAge(MAX_AGE_SECONDS);
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public JwtUtil jwtUtil() {

		return new JwtUtil(secretKey, customerRepository);
	}

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil) {

		return new JwtAuthenticationFilter(jwtUtil);
	}
}
