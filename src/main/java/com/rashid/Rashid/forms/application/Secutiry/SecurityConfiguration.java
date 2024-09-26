package com.rashid.Rashid.forms.application.Secutiry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	private UserPrincipalDetailsService userPrincipalDetailsService; 
	
	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService ; 
	}
	
	//data source 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	    
	
	
	
	/*IMPORTANT NOTE ---- DISABLE THIS CODE BEFORE DEPLOYMENT - AND DISABLE SSL CONFIG IN PROPERTIES FILE */
	//authorization //
	@Override
	protected void configure(HttpSecurity http)throws Exception {		
		http 
		        
//		.requiresChannel(channel -> 
//        channel.anyRequest().requiresSecure())  
		      
		.csrf().disable()  
		.authorizeRequests()
//		.antMatchers("/integrity/ping").permitAll()
		.antMatchers("/config/injectuser").permitAll()
		.antMatchers("/assets/**").permitAll()
		.antMatchers("/js/**").permitAll()  

		.antMatchers("/adminstration/**").hasAnyAuthority("owner","admin")
	
		.and()
		.exceptionHandling().accessDeniedPage("/forbidden")
		.and()
		.formLogin().defaultSuccessUrl("/adminstration/responsesAll")
		.loginPage("/login").permitAll()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
		;
	}
		
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider() ;
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		return daoAuthenticationProvider ; 
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
}
