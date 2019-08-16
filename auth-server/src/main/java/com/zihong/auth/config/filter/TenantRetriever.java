package com.zihong.auth.config.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.web.savedrequest.SavedRequest;

public interface TenantRetriever {
	
	String SPRING_SECURITY_SAVED_REQUEST = "SPRING_SECURITY_SAVED_REQUEST";
	String TENANT_KEY = "tenant";
	
	default public String obtainTenant(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		if(null == session) {
			return "";
		}
		
		SavedRequest savedRequest =  (SavedRequest) session.getAttribute(SPRING_SECURITY_SAVED_REQUEST);
		
		if(null == savedRequest) {
			return "";
		}
		
		String[] tenants = savedRequest.getParameterValues(TENANT_KEY);
		
		if(tenants == null || tenants.length == 0) {
			return "";
		}
		
		return tenants[0];
		
	}

}
