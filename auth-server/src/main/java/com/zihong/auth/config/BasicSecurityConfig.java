package com.zihong.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import com.zihong.auth.config.props.SecurityProperties;



@Configuration
@Order(1)
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private SecurityProperties securityProperties;

    private static final String ROLE_CLIENT = "CLIENT";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        	.withUser(securityProperties.getServiceAccount().getUserName())
        	.password(securityProperties.getServiceAccount().getPassword()).roles(ROLE_CLIENT); //We@lthCh0ng
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.antMatcher("/basic/**") //
        		.authorizeRequests().anyRequest().authenticated() //
                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint());;
    }
    
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        BasicAuthenticationEntryPoint entryPoint = 
          new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("admin realm");
        return entryPoint;
    }
}
