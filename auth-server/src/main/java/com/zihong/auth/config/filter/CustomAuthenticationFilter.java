package com.zihong.auth.config.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zihong.auth.config.token.CustomAuthenticationToken;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements TenantRetriever{
	
	
	
	
	public CustomAuthenticationFilter(String fakeUserName) {
		super();
		this.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler () {

		    @Override
		    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		            AuthenticationException exception) throws IOException, ServletException {
		    	String redirectUrl = "/login?errorin=";
		    	if(isWxUser(request)) {
		    		redirectUrl += "wx";
		    	}else {
		    		redirectUrl += "common";
		    	}
		        getRedirectStrategy().sendRedirect(request, response, redirectUrl);

		    }
		    
		    private boolean isWxUser( HttpServletRequest request) {
		    	try {
			    	SecurityContext context = (SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			    	if(null == context) {
			    		return false;
			    	}
			    	UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) context.getAuthentication();
			    	if(token == null) {
			    		return false;
			    	}
			    	User user = (User)token.getPrincipal();
			    	String username = user.getUsername();
			    	
			    	return fakeUserName.equalsIgnoreCase(username);
		    	}catch(Exception e) {
		    		//please add log
		    		e.printStackTrace();
		    	}
		    	
		    	return false;
		    }
		});
	}
	
		

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String tenant = obtainTenant(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();
		
		String scene = request.getSession().getId();

		CustomAuthenticationToken authRequest = new CustomAuthenticationToken(
				username, password, tenant ,scene);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	

}
