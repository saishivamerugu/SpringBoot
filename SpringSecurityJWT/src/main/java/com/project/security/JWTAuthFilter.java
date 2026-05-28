
package com.project.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JWTService jwtService;

	@Autowired
	private SecurityUserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String token;
		final String username;

		// Check whether Authorization header exists
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Remove Bearer from token
		token = authHeader.substring(7);

		try {
			// Extract username from JWT
			username = jwtService.extractUsername(token);
		}
		catch (Exception e) {
			filterChain.doFilter(request, response);
			return;
		}

		// Authenticate user if not already authenticated
		if (username != null &&
				SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails =
					userDetailsService.loadUserByUsername(username);

			// Validate JWT token
			if (jwtService.isTokenValid(token, userDetails.getUsername())) {

				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities());

				// Store authentication in SecurityContext
				SecurityContextHolder.getContext()
						.setAuthentication(authToken);
			}
		}

		// Forward request to next filter
		filterChain.doFilter(request, response);
	}
}


/*
 * written my own filter class - JWTAuthFilter
 * This Filter class is extended with onceperreqfilter .. it is an interface .. why we are implementing this request 
 * --
 * JWT should be invoked for every request .. 
 * 
 * /order - authenticate 
 /order again - authenticate .. 
  * 
  * all the same apis should be authenticated even if we hit the same apis multiple times
  * every time we has to validate the jwt filter .. as the name shows for every reuest check once .. and authenticate .. 
  * 
  * oncePerRequst have a method .. we have the requet and response and FIlterChain filterchain.. 
  * 
  * there are 100's of filters in the spring boot
  * 
  * all the filters are invoked one by one .. if the request comes .. 
  * then afer passing all the filters then request goto the Controller 
  * we has to pass the filter chain .. 
  * header
  * key - value 
  * auhorization - jwt 
 * from request i has to get a authorization .. headers are nothing but but key value pairs
 * for the key of authorization i has to get the value .. the value is jwt 
 * in the request im expecting a value 
 * 
 * Frontend get the token with Bearer jwt .. we have to remove the actual jwt from the token 
 * Ex : Bearer adkfjadjfaklejf.fajhsflaskf.adfhaaefjd
 * this is my header .. 
 * stored at auth Header 
 * for every request i get this value 
 * 
 * if there is no bearer or no jwt or no authHeader then we return .. 
 * and before that we foweard to next filter 
 * 
 * 
 * if token exists we remove the bearer  using substring method 
 * 
 * i pass jwt token adn i will extract the user name using a method ... from the exactUsername 
 * else return 
 * 
 * 
 * 
 * */
