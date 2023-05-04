package com.example.demo.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().requestMatchers("/", "/login", "/contacto", "/tienda" , "/css/**", "/js/**", "/imagenesLibros/**" , "/images/**").permitAll()
				.requestMatchers("/clientes/**").authenticated().anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login").defaultSuccessUrl("/").failureUrl("/login").permitAll()
				.and()
				.logout().permitAll().logoutSuccessUrl("/");
		return http.build();
	}
	
	 @Bean
	 public UserDetailsService userDetailsService() { 
	 UserDetails user1 =
	 User.withDefaultPasswordEncoder()
	 .username("user")
	 .password("password")
	 .roles("USUARIO")
	 .build();

	 UserDetails user2 =
	 User.withDefaultPasswordEncoder()
	 .username("admin")
	 .password("password")
	 .roles("ADMINISTRADOR")
	 .build();
	 return new InMemoryUserDetailsManager(user1, user2);
	 }
}

