package com.zihong.auth.config.token;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
	
	private static final long serialVersionUID = 686359129336590657L;
	
	private String tenantID;
	
	private String scene;
	

	public CustomAuthenticationToken(Object principal, Object credentials, String tenantID, String scene) {
		super(principal, credentials);
		this.tenantID = tenantID;
		this.scene = scene;
	}

	public CustomAuthenticationToken(Object principal, Object credentials, String tenantID,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials,authorities);
		this.tenantID = tenantID;
	}

	public String getTenantID() {
		return tenantID;
	}

	public void setTenantID(String tenantID) {
		this.tenantID = tenantID;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}
	
	
	
	

}
