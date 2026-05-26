package com.project.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	 @Bean
	 public PasswordEncoder passwordEncoder() {
	    	return new BCryptPasswordEncoder();
	    }
	 
	 @Bean
	    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	        http.authorizeHttpRequests(auth ->
	                auth.requestMatchers("/signup")
	                        .permitAll()
	                        .anyRequest()
	                        .authenticated())
	                .formLogin(Customizer.withDefaults()) 
	                .httpBasic(Customizer.withDefaults());
	        return http.build();
	    }
}
