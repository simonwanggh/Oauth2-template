package com.zihong.auth.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class CustomOAuth2AuthenticationToken extends UsernamePasswordAuthenticationToken {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5737389687729403549L;
	
	
	public CustomOAuth2AuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
		// TODO Auto-generated constructor stub
	}

	
	
	private String tenantID;



	

}
