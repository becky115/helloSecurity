package com.becky.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("SecurityConfig");
		System.out.println("SecurityConfig");
		System.out.println("SecurityConfig");
		System.out.println("SecurityConfig");
		System.out.println("SecurityConfig");
		System.out.println("SecurityConfig");
		//spring security roles and permissions from database
		//super.configure(http);
		
	}
}
