package com.ranjen.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	//method to configure users(in memory,database,ldap etc)
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication
		
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		//comma-delimeted list of roles , you can have any names for roles
		.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
		.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
		.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));
	}
	
	//method to configure security of web paths in application,login,logout etc
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated() //any request must be loggedin/authenticated
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage") //show our custom form at request mapping /showMyLoginPage
				.loginProcessingUrl("/authenticateTheUser") //login form should post data to this url for processing
				.permitAll() //allow everyone to see the login page, no need to login
				.and() //add logout support for default url /logout
				.logout().permitAll();
		//logut URL logic will be handled by Spring Security Filter 
		//which will invalidate user's HTTP session and remove session cookies
		//and append a log out parameter ?logout
		//no coding required
	}
}






