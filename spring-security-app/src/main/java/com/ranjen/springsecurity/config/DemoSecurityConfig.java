package com.ranjen.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// add a reference to our security data source
	//This reffering to the bean declared in the DemoAppConfig
	@Autowired
	private DataSource securityDataSource;

	@Override
	//method to configure users(in memory,database,ldap etc)
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// hardcoded - add our users for in memory authentication
		
/*		UserBuilder users = User.withDefaultPasswordEncoder();
		
		auth.inMemoryAuthentication()
		//comma-delimeted list of roles , you can have any names for roles
		.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
		.withUser(users.username("mary").password("test123").roles("EMPLOYEE", "MANAGER"))
		.withUser(users.username("susan").password("test123").roles("EMPLOYEE", "ADMIN"));*/
		
		//No more hardcoding users as now we will get the username and roles from the db
		
		// use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	
	//method to configure security of web paths in application,login,logout etc
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//without specific roles. just user being the authenticated - as commented below
/*		
 			http.authorizeRequests()
				.anyRequest().authenticated() //any request must be loggedin/authenticated
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage") //show our custom form at request mapping /showMyLoginPage
				.loginProcessingUrl("/authenticateTheUser") //login form should post data to this url for processing
				.permitAll() //allow everyone to see the login page, no need to login
				.and() //add logout support for default url /logout
				.logout().permitAll();
*/
		
		
		//restrict access based on specific roles
		http.authorizeRequests()
		.antMatchers("/").hasRole("EMPLOYEE") //homepage 
		.antMatchers("/leaders/**").hasRole("MANAGER") //** means all subdirectories
		.antMatchers("/systems/**").hasRole("ADMIN")
		.and()
		.formLogin()
			.loginPage("/showMyLoginPage")
			.loginProcessingUrl("/authenticateTheUser") //custom page access denied
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
		//logut URL logic will be handled by Spring Security Filter 
		//which will invalidate user's HTTP session and remove session cookies
		//and append a log out parameter ?logout
		//no coding required
		
		
	}
	
/*	This is based on our security datasource. It provides access to the database 
    for creating users. We'll also use JdbcUserDetailsManager to check if a user exists.
	The JdbcUserDetailsManager has all of the low-level JDBC code for accessing
	the security database. There is no need for us to create the JDBC code ...
	JdbcUserDetailsManager will handle it for us*/
	@Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		
		return jdbcUserDetailsManager; 
	}
}






