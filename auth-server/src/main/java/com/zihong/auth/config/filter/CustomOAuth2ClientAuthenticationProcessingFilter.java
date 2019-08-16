package com.zihong.auth.config.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.zihong.auth.config.token.CustomAuthenticationToken;

public class CustomOAuth2ClientAuthenticationProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter implements TenantRetriever{

	public CustomOAuth2ClientAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);		
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {		
		OAuth2Authentication origAuth =  (OAuth2Authentication) super.attemptAuthentication(request, response);	
		Authentication auth = origAuth.getUserAuthentication();
		
		return new OAuth2Authentication(origAuth.getOAuth2Request()
				,new CustomAuthenticationToken(auth.getPrincipal(),auth.getCredentials(),obtainTenant(request),auth.getAuthorities()));
	}
	
	
	

}
