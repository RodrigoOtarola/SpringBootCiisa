package com.example.demo;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig {

	/** Bean para encriptar contraseña */
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

		// Creamos usuario de prueba
		manager.createUser(
				User.withUsername("rodrigo@mail.cl").password(passwordEncoder().encode("1234")).roles("ADMIN").build());

		manager.createUser(User.withUsername("tania@mail.cl").password(passwordEncoder().encode("1234")).roles("EJECUTIVO").build());

		return manager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
		.headers(headers -> headers.frameOptions(frame -> frame.disable()))
		
		
		// Creamos objeto para restringir accesos
		.authorizeHttpRequests(authorize -> authorize				
				.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/layouts/**")).permitAll()				
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/usuarios/**")).hasRole("ADMIN")
				.anyRequest().authenticated())
		//.formLogin(login -> login.loginPage("/login").permitAll())
		.formLogin(login -> login.permitAll())
		.logout(logout -> logout.permitAll())
		.exceptionHandling(exception -> exception.accessDeniedPage("/error_403"));
		
		return http.build();

	}
}
