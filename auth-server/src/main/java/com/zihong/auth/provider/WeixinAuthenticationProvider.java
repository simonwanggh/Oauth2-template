package com.zihong.auth.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.zihong.auth.config.token.CustomAuthenticationToken;

public class WeixinAuthenticationProvider implements AuthenticationProvider{
	
	@Value("${wechat.fake.username}")
	private String fakeWxUserName;
	
	@Value("${wechat.fake.password}")
	private String fakeWxPassword;
	
	@Autowired
	private WxUserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(isNotWxLogin(authentication)) {
			return null;
		}		
		
		return userService.authenticate(authentication);
	}

	private boolean isNotWxLogin(Authentication authentication) {
			return (authentication == null) 
				|| !((String)authentication.getPrincipal()).equalsIgnoreCase(fakeWxUserName)
				|| !((String)authentication.getCredentials()).equalsIgnoreCase(fakeWxPassword);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(CustomAuthenticationToken.class);
	}

}
