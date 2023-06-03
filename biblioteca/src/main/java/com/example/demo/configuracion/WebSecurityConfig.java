package com.example.demo.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests()
				.requestMatchers("/", "/login", "/contacto", "/contacto/**", "/adminContacto", "/adminContacto/**",
						"/solicitud/**", "/usuario", "/MisSolicitudes", "/tienda", "/tienda/**", "/css/**", "/js/**",	//TODOS
						"/imagenesLibros/**", "/images/**", "/registro", "/usuarios/saveNuevoUsuario",
						"/usuarios/saveNuevoUsuario/**", "/usuarios/save", "/foro", "/foro/**", "/usuario/**")
				.permitAll()
				.requestMatchers("/zonaPremium" , "/zonaPremium/**" , "/usuarios/{usernameUsuario}/confirmacionCorreo", "/estadisticas", "/misMultas/{nombreUsuario}") //AUTENTICADOS SOLO
				.hasAnyRole("USER", "ADMIN")
				.requestMatchers("/adminIndex", 
						"/usuario/{idUsuario}/adminAlquiler",
						"/usuario/{idUsuario}/adminAlquiler/add", 
						"/usuario/{idUsuario}/adminAlquiler/save",				//ADMINS SOLO
						"/usuario/{idUsuario}/alquilar/{idLibro}", 
						"/adminLibros/{isbnLibro}/adminEjemplares",
						"/adminLibros", 
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas",
						"/usuario/{idUsuario}/adminMultas",
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/add",
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/save",
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/delete/{idMulta}",
						"/usuario/{idUsuario}/adminMultas/{idMulta}/descartar", "/adminContacto",
						"/adminContacto/{idSolicitud}/adminContactoform", "/adminClientes",
						"/usuario/{idUsuario}/adminEditarUsuarios",
				        "/buscador/**")
				.hasRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/").failureUrl("/login?error").permitAll().and().logout().permitAll()
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/errors/403");
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	

}
