package com.example.lottery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withUsername("jack").password("{noop}secret").roles("USER").build();
		return new InMemoryUserDetailsManager(user);
	}
}
