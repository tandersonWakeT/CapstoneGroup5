package com.hcl.ecommerce.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	
	static Logger log = Logger.getLogger(SecurityConfiguration.class);
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.info("Configuring safety of the backend with https");
		// protect endpoint /api/orders
		http.authorizeRequests()
				.antMatchers("/api/orders/**")
				.authenticated()
				.and()
				.oauth2ResourceServer()
				.jwt();
		
		// add support for CORS filter
		http.cors();
		
		// force a non-empty response body for 401's to make the response more friendly
		Okta.configureResourceServer401ResponseBody(http);
		
		log.info("Set up support for CORS and 401 response page");
		
		// disable CSRF since we are not using Cookies for session tracking
		http.csrf().disable();
	}
}
