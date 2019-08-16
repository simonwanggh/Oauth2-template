package com.zihong.auth.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
public class ProviderConfig {
	@Bean
	@Qualifier("phoneAuthProvider")
	public AuthenticationProvider getPhoneAuthenticationProvider() {
		return new PhoneAuthenticationProvider();
	}
	
	@Bean
	@Qualifier("dbAuthProvider")
	public AuthenticationProvider getDBAuthenticationProvider() {
		return new DBAuthenticationProvider();
	}
	
	@Bean
	@Qualifier("wxAuthProvider")
	public AuthenticationProvider getWxAuthenticationProvider() {
		return new WeixinAuthenticationProvider();
	}

}
