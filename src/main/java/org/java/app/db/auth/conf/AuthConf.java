package org.java.app.db.auth.conf;

import org.java.app.db.auth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class AuthConf {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		
			//http.authorizeHttpRequests()
			http.csrf().disable()
			
				.authorizeHttpRequests()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/**").permitAll()
				.requestMatchers("/api/v1.0/**").permitAll()
				.requestMatchers(new RegexRequestMatcher("/pizza/[0-9]+", null)).hasAnyAuthority("USER", "ADMIN")
				.requestMatchers("/pizza/**").hasAuthority("ADMIN")
				.and().formLogin()
				.and().logout();
			
			return http.build();
	}
	
	@Bean
	UserService userDetailsService() {
	return new UserService();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailsService());
	authProvider.setPasswordEncoder(passwordEncoder());
	return authProvider;
	}
}
