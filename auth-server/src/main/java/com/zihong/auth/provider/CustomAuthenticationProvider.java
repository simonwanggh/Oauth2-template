package com.zihong.auth.provider;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import com.zihong.auth.config.token.CustomAuthenticationToken;

public abstract class CustomAuthenticationProvider implements AuthenticationProvider {
	
	protected final static String COMMON_USER = "COMMON_USER";
	
	@Autowired
	private PasswordEncoder  passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		CustomAuthenticationToken customAuth = (CustomAuthenticationToken)authentication;
		String userName = customAuth.getName();
        String password = customAuth.getCredentials()
            .toString();
        String tenant = customAuth.getTenantID();
        
        
        if (!StringUtils.hasText(userName)) {
            throw new BadCredentialsException("没有提供用户名");
        }
        
        if (!StringUtils.hasText(password)) {
            throw new BadCredentialsException("没有提供密码");
        }
        
        if(userName.equalsIgnoreCase("admin") && password.equals("admin")) {
        	//test user will be removed.
        	return new CustomAuthenticationToken(userName, password, tenant, retrieveAuthorities());    
        }
 
        UserDetails userDetails =  getUserService().loadUserByUsername(userName);
        
        if(userDetails == null || !StringUtils.hasText(userDetails.getUsername())) {
        	throw new BadCredentialsException("用户不存在");
        }
        
        if(passwordEncoder.matches(password, userDetails.getPassword())) {
        	return new CustomAuthenticationToken(userName, password, "test tenant id", retrieveAuthorities());
        }else {
        	throw new BadCredentialsException("密码错误");
        }
	}

	protected abstract Collection<? extends GrantedAuthority> retrieveAuthorities();

	protected abstract UserDetailsService getUserService();

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(CustomAuthenticationToken.class);
	}

}
