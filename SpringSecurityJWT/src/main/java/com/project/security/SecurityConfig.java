package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	JWTAuthFilter jwtAuthFilter;

	// Password encoder bean
	// used to encrypt password before storing into database
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
			throws Exception {

		http

			// Disable csrf
			// because we are using JWT token authentication
			.csrf(csrf -> csrf.disable())

			// Disable cors
			.cors(cors -> cors.disable())

			// Configure authorization rules
			.authorizeHttpRequests(auth ->

				auth

				// signup and login should be public
				.requestMatchers("/auth/**","/open")
				.permitAll()

				// except above all requests should be authenticated
				.anyRequest()
				.authenticated())

			// JWT is stateless
			// server should not create session
			.sessionManagement(session ->
				session.sessionCreationPolicy(
						SessionCreationPolicy.STATELESS))

			// Before UsernamePasswordAuthenticationFilter
			// execute my JWTAuthFilter
			.addFilterBefore(jwtAuthFilter,
					UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authConfig)
			throws Exception { 

		// AuthenticationManager is responsible
		// for authenticating username and password

		return authConfig.getAuthenticationManager();
	}
}
