package com.produtos.apirest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.produtos.apirest.resource.ImplementsUserDatailService;

@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class Security extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementsUserDatailService userDetailService;
 
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Urls that do not need authorization
        web.ignoring().antMatchers(
    		"/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**"
        );
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Authorization role by request and url
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/categories").hasRole("ADMIN")
			.antMatchers("/api/coupon").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/coupon/{id}").hasAnyAuthority()
			.antMatchers("/api/category").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/category/{id}").hasAnyAuthority()
			.antMatchers("/api/discount").hasRole("ADMIN")
			.antMatchers(HttpMethod.GET, "/api/discount/{id}").hasAnyAuthority()
			.antMatchers("/api/user").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Encoder method to user login
		return new BCryptPasswordEncoder();
	}
}
