package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Configuration
public class SpringSecurityConfig {

	// Importamos el service de usuario
	@Autowired
	private UsuarioService usuarioService;

	/** Bean para encriptar contraseña */
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() throws Exception {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		List<Usuario> usuarios = usuarioService.findAll();
		for (Usuario usuario : usuarios) {
			//Obtenemos el nombre del rol
			String[] roles = { usuario.getRol().getNombre() };
			manager.createUser(
					User.withUsername(usuario.getEmail())
					.password(usuario.getPassword())
					.roles(roles)
					.build()
					);
		}

		// Creamos usuario de prueba
		/*manager.createUser(
				User.withUsername("rodrigo@mail.cl").password(passwordEncoder().encode("1234")).roles("ADMIN").build());

		manager.createUser(User.withUsername("tania@mail.cl").password(passwordEncoder().encode("1234"))
				.roles("EJECUTIVO").build());*/

		
		return manager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
				.headers(headers -> headers.frameOptions(frame -> frame.disable()))

				// Creamos objeto para restringir accesos
				.authorizeHttpRequests(
						authorize -> authorize.requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
								.requestMatchers(new AntPathRequestMatcher("/admin/**")).permitAll()
								.requestMatchers(new AntPathRequestMatcher("/layouts/**")).permitAll()
								.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
								
								//Regla de usuarios
								.requestMatchers(new AntPathRequestMatcher("/usuarios/**")).hasRole("Administrador")
								
								//Reglas de comunas
								.requestMatchers(new AntPathRequestMatcher("/comunas/crear")).hasAnyRole("Administrador", "Ejecutivo")
								.requestMatchers(new AntPathRequestMatcher("/comunas/editar/*")).hasAnyRole("Administrador", "Ejecutivo")
								.requestMatchers(new AntPathRequestMatcher("/comunas/eliminar/*")).hasAnyRole("Administrador", "Ejecutivo")
								
								//Reglas de proyecto
								.requestMatchers(new AntPathRequestMatcher("/proyectos/crear")).hasAnyRole("Administrador", "Ejecutivo")
								.requestMatchers(new AntPathRequestMatcher("/proyectos/editar/**")).hasAnyRole("Administrador", "Ejecutivo")
								.requestMatchers(new AntPathRequestMatcher("/proyectos/eliminar/**")).hasAnyRole("Administrador", "Ejecutivo")
								
								//Reglas de modelo
								.requestMatchers(new AntPathRequestMatcher("/modelos/crear")).hasAnyRole("Administrador", "Ejecutivo")
								.requestMatchers(new AntPathRequestMatcher("/modelos/editar/**")).hasAnyRole("Administrador", "Ejecutivo")
								.requestMatchers(new AntPathRequestMatcher("/modelos/eliminar/**")).hasAnyRole("Administrador", "Ejecutivo")
									
								.anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
				// .formLogin(login -> login.permitAll())
				.logout(logout -> logout.permitAll())
				.exceptionHandling(exception -> exception.accessDeniedPage("/error_403"));

		return http.build();

	}
}
