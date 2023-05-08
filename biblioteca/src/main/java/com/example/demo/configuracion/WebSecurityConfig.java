package com.example.demo.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests()
				.requestMatchers("/", "/login", "/contacto", "/tienda", "/css/**", "/js/**", "/imagenesLibros/**",
						"/images/**")
				.permitAll()
				.requestMatchers("/adminIndex", 
						"/usuario/{idUsuario}/adminAlquiler",
						"/usuario/{idUsuario}/adminAlquiler/add", 
						"/usuario/{idUsuario}/adminAlquiler/save",
						"/usuario/{idUsuario}/alquilar/{idLibro}", 
						"/adminLibros/{isbnLibro}/adminEjemplares",
						"/adminLibros", 
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas",
						"/usuario/{idUsuario}/adminMultas",
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/add",
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/save",
						"/usuario/{idUsuario}/adminAlquiler/{idAlquiler}/adminMultas/delete/{idMulta}",
						"/usuario/{idUsuario}/adminMultas/{idMulta}/descartar", 
						"/adminContacto",
						"/adminContacto/{idSolicitud}/adminContactoform", 
						"/adminClientes",
						"/usuario/{idUsuario}/adminEditarUsuarios", 
						"/usuarios/save")
				.hasRole("ADMIN").anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login")
				.defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
				.and()
				.logout().permitAll().logoutSuccessUrl("/")
				.and()
				.exceptionHandling().accessDeniedPage("/errors/403");
		return http.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
	build.userDetailsService(userDetailsService).passwordEncoder(new
	BCryptPasswordEncoder());
	 }
}
