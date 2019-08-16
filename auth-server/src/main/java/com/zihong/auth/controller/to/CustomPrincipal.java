package com.zihong.auth.controller.to;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.json.JSONException;
import org.springframework.security.core.GrantedAuthority;

public class CustomPrincipal implements Principal {
	
	private String name;
	private String clientId;
	private Collection<? extends GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	
	public CustomPrincipal(String name, String clientId, Collection<? extends GrantedAuthority> authorities) {
		this.name = name;
		this.clientId = clientId;
		this.authorities = Collections.unmodifiableCollection(authorities);
	}

	@Override
	public String getName() {
		return name;
	}

	public String getClientId() {
		return clientId;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public PHPTemp getTempForPHP() throws JSONException {
		return new PHPTemp();
	}
	
	

}
