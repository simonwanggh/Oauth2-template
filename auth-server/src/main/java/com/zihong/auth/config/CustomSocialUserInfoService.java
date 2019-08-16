package com.zihong.auth.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

public class CustomSocialUserInfoService extends UserInfoTokenServices {

	public CustomSocialUserInfoService(String userInfoEndpointUrl, String clientId) {
		super(userInfoEndpointUrl, clientId);
		// TODO Auto-generated constructor stub
	}

}
