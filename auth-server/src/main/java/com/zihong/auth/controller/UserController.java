package com.zihong.auth.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zihong.auth.config.token.CustomAuthenticationToken;
import com.zihong.auth.controller.to.CustomPrincipal;


@RestController
public class UserController {
	
    
    @RequestMapping("/user")
    public CustomPrincipal user(Principal principal) {
    	String clientId = "";
    	Collection<? extends GrantedAuthority> authorities = Collections.EMPTY_LIST;
    	if(principal instanceof OAuth2Authentication) {
    		OAuth2Authentication oAuthAuth = (OAuth2Authentication)principal;
    		Authentication auth = oAuthAuth.getUserAuthentication();
    		if(auth instanceof CustomAuthenticationToken) {
	    		CustomAuthenticationToken token = (CustomAuthenticationToken)oAuthAuth.getUserAuthentication();	    		
	    		clientId = token.getTenantID(); 
    		}
    		authorities = oAuthAuth.getAuthorities();
    	}
    	return new CustomPrincipal(principal.getName(),clientId,authorities);
    }

}
