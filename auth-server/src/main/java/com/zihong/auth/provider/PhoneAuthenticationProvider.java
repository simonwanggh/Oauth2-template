package com.zihong.auth.provider;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;


public class PhoneAuthenticationProvider extends CustomAuthenticationProvider {
	
	@Autowired
	@Qualifier("phoneUserService")
	private UserDetailsService userService;	


	@Override
	protected Collection<? extends GrantedAuthority> retrieveAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority(COMMON_USER));
	}

	@Override
	protected UserDetailsService getUserService() {
		return userService;
	}

}
