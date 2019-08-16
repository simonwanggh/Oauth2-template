package com.zihong.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.client.RestTemplate;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@EnableOAuth2Client
public class WebOauth2ClientConfig {

//	@Autowired
//	private OAuth2ClientContext oauth2ClientContext;
//
//	@Bean
//	public OAuth2RestTemplate restTemplate() {
//		return new OAuth2RestTemplate(new AuthorizationCodeResourceDetails(), oauth2ClientContext);
//	}
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TOKEN_TYPE = "Bearer";
	
	@Bean
	public RequestInterceptor requestTokenBearerInterceptor() {
		
		return new RequestInterceptor() {
	        @Override
	        public void apply(RequestTemplate template) {
		
				SecurityContext securityContext = SecurityContextHolder.getContext();
				Authentication authentication = securityContext.getAuthentication();
		
				if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
					OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
					template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
				}
	        }
		};
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
