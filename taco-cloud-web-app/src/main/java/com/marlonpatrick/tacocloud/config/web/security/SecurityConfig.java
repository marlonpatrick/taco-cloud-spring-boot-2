package com.marlonpatrick.tacocloud.config.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/order/**", "/taco/**").authenticated()
			.antMatchers("/user/register").anonymous()
			.antMatchers("/login").anonymous()
			.antMatchers("/", "/**").permitAll()
		    .and()
		    .formLogin().loginPage("/login")
		    .and()
		    .logout().logoutSuccessUrl("/");


		//-----------------------ACCESS H2 CONSOLE
		http.csrf().disable();
		http.headers().frameOptions().disable();
		//-----------------------ACCESS H2 CONSOLE
	}

	@SuppressWarnings("unused")
	private void inMemoryAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder = passwordEncoder();

		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser("patrick")
				.password(passwordEncoder.encode("marlon")).authorities("ROLE_USER").and().withUser("marlon")
				.password(passwordEncoder.encode("patrick")).authorities("ROLE_USER");
	}
}
